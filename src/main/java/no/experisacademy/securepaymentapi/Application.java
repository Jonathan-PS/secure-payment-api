package no.experisacademy.securepaymentapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.model.Token;
import no.experisacademy.securepaymentapi.repositories.ProductRepository;
import no.experisacademy.securepaymentapi.repositories.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.stripe.Stripe;
import com.stripe.model.Customer;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application {

	private static String secretApiKey ="sk_test_5B0GI5Lt8GUHvvptHkURkfY000Xj6Tvvii";
	private static String newCustomerCusId = "cus_ErjwhB11tYVZGH";
	private static String olaNormannCusId = "cus_ErLVczAX1v7jpH";

	@Autowired
	ProductRepository repository;
	public static void main(String[] args) throws StripeException{
		/*
		 * There are two ways of authenticating requests when
		 * using the Stripe API on behalf of a connected account
		 * --> Using the Stripe-Account header and the connected account’s ID
		 * --> Using the connected account’s API keys
		 *
		 * You should use the Stripe-Account header method when possible: it’s easier and more secure.
		 * The second approach is supported for legacy compatibility, but generally shouldn’t be used
		 * in new applications. Both approaches require that you store the connected account’s
		 * information—ID or API keys–when connecting the account to your platform.
		 * Read more: https://stripe.com/docs/connect/authentication
		 */

		// LESS SECURE: Connected account’s SECRET API KEY
		Stripe.apiKey=secretApiKey;
		//Stripe.apiKey = "{{PLATFORM_SECRET_KEY}}";
		// MORE SECURE: Stripe-Account header and the connected account’s ID
		//RequestOptions requestOptions = RequestOptions.builder().setStripeAccount(CONNECTED_STRIPE_ACCOUNT_ID).build();


		/*_____________________ USING METHODS _____________________*/

		// CREATE NEW CUSTOMER
		//createCustomer("newCustomer@cus.com");

		// GET CUSTOMER INFO (FROM CUSTOMER ID)
		//getCustomerInfoFromId("cus_ErLVczAX1v7jpH");
		//getCustomerInfoFromId("cus_ErjwhB11tYVZGH");

		// CREATE CARD (FOR CUSTOMER ID)
		/*
		createCardForId("cus_ErjwhB11tYVZGH",	"4242_4242_4242_4242",
						"04", "2020", "123");
		*/

		// ADD PAYMENT (FOR CUSTOMER ID)
		//addPaymentsCusId("cus_ErjwhB11tYVZGH", 2000, "nok");
		//addPaymentsCardId("cus_ErjwhB11tYVZGH", 3000, "nok", "tok_visa");

		// RUN SPRING APPLICATION
		SpringApplication.run(Application.class, args);
	}


	/* _________________________ METHODS _________________________*/

	/**
	 *
	 * @param cus_id
	 * @throws StripeException
	 */
	public static void getCustomerInfoFromId(String cus_id) throws StripeException {
		// Get customer info from id
		Customer customer = Customer.retrieve(cus_id);

		// Print to console
		System.out.println("_____CUSTOMER INFO for id :"+cus_id+"_____");
		System.out.println("\nGet last resonse: "+customer.getLastResponse().requestId());
		System.out.println("\nCustomer info: ");
		gsonPrettyPrint(customer);
	}

	/**
	 * Create a Stripe customer
	 * @param email from input
	 * @throws StripeException
	 */
	public static void createCustomer(String email) throws StripeException {
		// Create new Customer with email
		Map<String, Object> customerParameter = new HashMap<String, Object>();
		customerParameter.put("email", email); // ("email", "ola@normann.com")
		Customer newCustomer = Customer.create(customerParameter);

		// Print to console
		System.out.println("\nNew customer created!\n\tEmail: "+email+"\n\tID: "+newCustomer.getId()+"\n");
	}

	/**
	 *
	 * @param cus_id
	 * @param cardNum
	 * @param exp_month
	 * @param exp_year
	 * @param cvc
	 * @throws StripeException
	 */
	public static void createCardForId(String cus_id, String cardNum, String exp_month,
									   String exp_year, String cvc) throws StripeException{

		// Set card parameters
		Customer customer = Customer.retrieve(cus_id);
		Map<String, Object> cardParam = new HashMap<String, Object>();
		cardParam.put("number",cardNum);
		cardParam.put("exp_month",exp_month);
		cardParam.put("exp_year",exp_year);
		cardParam.put("cvc",cvc);

		// Add card parameters to tokenParam
		Map<String, Object> tokenParam = new HashMap<String, Object>();
		tokenParam.put("card", cardParam);

		// Create token
		Token token = Token.create(tokenParam);

		// Add token parameters to source
		Map<String, Object> source = new HashMap<String, Object>();
		source.put("source", token.getId());

		// Add token to Customer
		customer.getSources().create(source);

		// Print to console
		System.out.println("Customer card for id "+ cus_id +"is created!");
		gsonPrettyPrint(customer);
		System.out.println("Token: ");
		gsonPrettyPrint(token);
	}
	/**
	 * ADD PAYMENTS
	 * @param cus_id
	 * @throws StripeException
	 */
	public static void addPaymentsCusId(String cus_id, int amount, String currency, String description) throws StripeException {

		Customer customer = Customer.retrieve(cus_id);
		Map<String, Object> chargeParam = new HashMap<String, Object>();

		// Convert params to correct Stripe params
		String amountString = Integer.toString(amount);
		String currencyToLower = currency.toLowerCase();

		// Add payment parameters
		chargeParam.put("amount", amountString); // 100 = 1.00 currency
		chargeParam.put("currency",currencyToLower); // for cents min 50
		chargeParam.put("description", description);
		chargeParam.put("customer",customer.getId()); // USING CUSTOMER ID (default card) and NOT SOURCE/CARD!
		//chargeParam.put("source", "tok_mastercard");
		// ^ obtained with Stripe.js

		// METADATA - Order_id
		Map<String, String> initialMetadata = new HashMap<String, String>();
		initialMetadata.put("order_id", "6735");
		chargeParam.put("metadata", initialMetadata);

		// Create Payment with parameters
		Charge.create(chargeParam);

		// Print to console
		System.out.println("Customer Payment for id "+ cus_id +"is created!");
		gsonPrettyPrint(customer);
	}

	public static void addPaymentsCardId(String cus_id, int amount, String currency, String cardId) throws StripeException {

		Customer customer = Customer.retrieve(cus_id);
		Map<String, Object> chargeParam = new HashMap<String, Object>();

		// Convert params to correct Stripe params
		String amountString = Integer.toString(amount);
		String currencyToLower = currency.toLowerCase();

		// Add payment parameters
		chargeParam.put("amount", amountString); // 100 = 1.00 currency
		chargeParam.put("currency",currencyToLower); // for cents min 50
		chargeParam.put("source",cardId); // USING CARD ID and NOT CUSTOMER ID!

		// METADATA - Order_id
		Map<String, String> initialMetadata = new HashMap<String, String>();
		initialMetadata.put("order_id", "6735");
		chargeParam.put("metadata", initialMetadata);

		// Create Payment with parameters
		Charge.create(chargeParam);

		// Print to console
		System.out.println("Customer Payment for id "+ cus_id +"is created!");
		gsonPrettyPrint(customer);
		//System.out.println("Token: ");
		//gsonPrettyPrint(token);

	}

	/**
	 * Use Gson to pretty-print Json
	 * @param obj from input
	 */
	public static void gsonPrettyPrint(Object obj) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(obj));
	}

}
