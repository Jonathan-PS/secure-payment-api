package no.experisacademy.securepaymentapi;


import com.stripe.model.*;
import no.experisacademy.securepaymentapi.repositories.ProductRepository;
import no.experisacademy.securepaymentapi.repositories.RegisteredUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;

import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
public class Application {

	private static String secretApiKey ="sk_test_5B0GI5Lt8GUHvvptHkURkfY000Xj6Tvvii";

  
	/*@Autowired
	ProductRepository repository; */
	public static void main(String[] args) throws StripeException{

		// LESS SECURE: Connected account’s SECRET API KEY,
		// MORE SECURE: Stripe-Account header and the connected account’s ID
		Stripe.apiKey=secretApiKey;


		/*_____________________ USING METHODS _____________________*/

		// CREATE NEW CUSTOMER
		//createCustomer("newCustomer@cus.com");

		// GET CUSTOMER INFO (FROM CUSTOMER ID)
		//getCustomerInfoFromId("cus_ErLVczAX1v7jpH");
		//getCustomerInfoFromId("cus_ErjwhB11tYVZGH");

		// CREATE CARD (FOR CUSTOMER ID)
		//createCardForId("cus_ErLVczAX1v7jpH",	"4000005780000007","04", "2020", "123");

		// ADD PAYMENT (FOR CUSTOMER ID)
		//addPaymentDefaultCard("cus_ErjwhB11tYVZGH", 2000, "nok");
		//addPaymentSelectCard("cus_ErjwhB11tYVZGH","card_1EOAxYDNGKvGPvcfQVmRK9J0", 3000, "nok", "tok_visa");

		// RUN SPRING APPLICATION
		SpringApplication.run(Application.class, args);
	}


	/* _________________________ METHODS _________________________*/

	/**
	 * Get Customer Info
	 *
	 * @param cusId Customer ID
	 * @throws StripeException ExceptionHandling
	 */
	public static void getCustomerInfoFromId(String cusId) throws StripeException {
		// Get customer info from id
		Customer customer = Customer.retrieve(cusId);

		// Print to console
		System.out.println("_____CUSTOMER INFO for id :"+cusId+"_____");
		System.out.println("\nGet last resonse: "+customer.getLastResponse().requestId());
		System.out.println("\nCustomer info: ");
		gsonPrettyPrint(customer);
	}

	/**
	 * Create a Stripe customer
	 *
	 * @param email from input
	 * @throws StripeException ExceptionHandling
	 */
	public static void createCustomer(String email) throws StripeException {
		// Create new Customer with email
		Map<String, Object> customerParameter = new HashMap<String, Object>();
		customerParameter.put("email", email); // ("email", "ola@normann.com")
		Customer newCustomer = Customer.create(customerParameter);

		// Print to console
		System.out.println("\nNew customer created!\n\tEmail: "+email+"\n\tID: "+newCustomer.getId()+"\n");
	}

	public static void createCardForId(String cusId, String cardNum, String expMonth,
									   String expYear, String cvc) throws StripeException{
		// Set card parameters
		Customer customer = Customer.retrieve(cusId);
		PaymentSourceCollection allCardDetails = customer.getSources();
		Map<String, Object> cardParam = new HashMap<String, Object>();
		cardParam.put("number",cardNum);
		cardParam.put("exp_month",expMonth);
		cardParam.put("exp_year",expYear);
		cardParam.put("cvc",cvc);

		// Add card parameters to tokenParam
		Map<String, Object> tokenParam = new HashMap<String, Object>();
		tokenParam.put("card", cardParam);

		// Create token
		Token token = Token.create(tokenParam);

		// CHECK IF CARD ALREADY EXIST
		boolean cardIsNotExist = true;
		Gson gson = new Gson();
		Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();

		for (int i=0; i<allCardDetails.getData().size(); i++) {
			String jsonObject = gsonPretty.toJson(allCardDetails.getData().get(i));
			Card card = gson.fromJson(jsonObject, Card.class);
			if (card.getFingerprint().equals(token.getCard().getFingerprint())) {
				cardIsNotExist = false;
				break;
			}
		}

		if (cardIsNotExist) {
			// Add token parameters to source
			Map<String, Object> source = new HashMap<String, Object>();
			source.put("source", token.getId());

			// Add token to Customer
			customer.getSources().create(source);

			// Print to console
			System.out.println("Customer card for id "+ cusId +"is created!\n");
			System.out.println("Customer ID: "+customer.getId());
			System.out.println("Customer email: "+customer.getEmail());
			System.out.println("Card ID: "+customer.getDefaultSource());
			System.out.println("Currency: "+customer.getCurrency());
			System.out.println("Shipping: ");
			gsonPrettyPrint(customer.getShipping());
			//gsonPrettyPrint(customer);
			//System.out.println("Token (but prints Response body): ");
			//gsonPrettyPrint(token);
		} else {
			System.out.println("Card already exist!");
		}
	}

	/**
	 * ADD PAYMENTS
	 *
	 * @param cusId Customer ID
	 * @throws StripeException ExceptionHandling
	 */
	public static void addPaymentDefaultCard(String cusId, int amount, String currency, String description) throws StripeException {

		Customer customer = Customer.retrieve(cusId);
		Map<String, Object> chargeParam = new HashMap<String, Object>();

		// Convert params to correct Stripe params
		String amountString = Integer.toString(amount);
		String currencyToLower = currency.toLowerCase();

		// Add payment parameters
		chargeParam.put("amount", amountString); // 100 = 1.00 currency
		chargeParam.put("currency",currencyToLower); // for cents min 50
		chargeParam.put("description", description);
		chargeParam.put("customer",customer.getId()); // Using Customer ID (and get default card!)
		//chargeParam.put("source", "tok_mastercard");
		// ^ obtained with Stripe.js

		// METADATA - Order_id
		Map<String, String> initialMetadata = new HashMap<String, String>();
		initialMetadata.put("order_id", "1234");
		chargeParam.put("metadata", initialMetadata);

		// Create Payment with parameters
		Charge charge = Charge.create(chargeParam);

		// Print to console
		System.out.println("Customer Payment for id "+ cusId +"is created!");
		gsonPrettyPrint(charge);
	}

	/**
	 * Add Payments
	 *
	 * @param cusId Customer ID
	 * @param amount Amount
	 * @param currency currency (nok, usd..)
	 * @param cardId Card ID
	 * @throws StripeException ExceptionHandling
	 */
	public static void addPaymentSelectCard(String cusId, String cardId, int amount, String currency) throws StripeException {

		Customer customer = Customer.retrieve(cusId);
		Map<String, Object> chargeParam = new HashMap<String, Object>();

		// Convert params to correct Stripe params
		String amountString = Integer.toString(amount);
		String currencyToLower = currency.toLowerCase();

		// Add payment parameters
		chargeParam.put("amount", amountString); // 100 = 1.00 currency
		chargeParam.put("currency",currencyToLower); // for cents min 50
		chargeParam.put("customer",cusId);
		chargeParam.put("source",cardId); // Selecting card by card id

		// METADATA - Order_id
		Map<String, String> initialMetadata = new HashMap<String, String>();
		initialMetadata.put("order_id", "1234");
		chargeParam.put("metadata", initialMetadata);

		// Create Payment with parameters
		Charge charge = Charge.create(chargeParam);

		// Print to console
		System.out.println("Customer Payment for id "+ cusId +"is created!");
		gsonPrettyPrint(charge);
		//System.out.println("Token: ");
		//gsonPrettyPrint(token);

	}

	/**
	 * Use Gson to pretty-print Json
	 *
	 * @param obj from input
	 */
	public static void gsonPrettyPrint(Object obj) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(obj));
	}

	public static String gsonPrettyToJson(Object obj) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(obj);
	}

}
