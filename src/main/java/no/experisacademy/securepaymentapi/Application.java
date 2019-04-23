package no.experisacademy.securepaymentapi;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootApplication
public class Application {

	private static String secretApiKey ="sk_test_5B0GI5Lt8GUHvvptHkURkfY000Xj6Tvvii";

  

	public static void main(String[] args) throws StripeException{

		// LESS SECURE: Connected account’s SECRET API KEY,
		// MORE SECURE: StripePayment-Account header and the connected account’s ID
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
		//public static void addPaymentFromCard(String cardId, int amount, String currency) throws StripeException {
		//addPaymentWithToken("tok_1EQ9wgDNGKvGPvcfLG7pT8CL", 500, "NOK");

		// THESE ONES ArRE LATEST
		//printCustomerIdByEmail("ola@nordmann.com");
		// addPaymentFromEmailDefaultCard("ola@nordmann.com", 52525, "nok", "Purchase at: Secure-payment-client");
		// getCardIdFromLast4("helene.harmens@gmail.com", "4444");


		//getCustomerIdByEmail("ola@nordmann.com");


		//getCustomerIdByEmail("test@kanSlettes.no");

		// RUN SPRING APPLICATION
		SpringApplication.run(Application.class, args);
	}


	/* _________________________ METHODS _________________________*/

	/*public static void createCustomer(String email) throws StripeException {
		// Create new Customer with email
		Map<String, Object> customerParameter = new HashMap<String, Object>();
		customerParameter.put("email", email); // ("email", "ola@normann.com")
		Customer newCustomer = Customer.create(customerParameter);

		// Print to console
		System.out.println("\nNew customer created!\n\tEmail: "+email+"\n\tID: "+newCustomer.getId()+"\n");
	}

	public static String getCustomerIdByEmail(String email) throws StripeException {
		// Get Customer ID from email

		Map<String, Object> options = new HashMap<>();
		options.put("email", email);
		List<Customer> customers = Customer.list(options).getData();
		System.out.println(customers);

		if (customers.size() > 0) {
			Customer customer = customers.get(0);
			String cusId = customer.getId();
			System.out.println("Customer with email '" + email + "' is "+ cusId);
			return cusId;
		} else {
			System.out.println("No customers with that email");
			createCustomer(email);
			System.out.println("New customer created in Stripe");
			return "New customer created in Stripe";
		}
		//System.out.println(customers);
	}*/
	// MOVED METHODS TO StripeService.java
	/*public static void getCustomerInfoFromId(String cusId) throws StripeException {
		// Get customer info from id
		Customer customer = Customer.retrieve(cusId);

		// Print to console
		System.out.println("_____CUSTOMER INFO for id :"+cusId+"_____");
		System.out.println("\nGet last resonse: "+customer.getLastResponse().requestId());
		System.out.println("\nCustomer info: ");
	}

	public static void createCustomer(String email) throws StripeException {
		// Create new Customer with email
		Map<String, Object> customerParameter = new HashMap<String, Object>();
		customerParameter.put("email", email); // ("email", "ola@normann.com")
		Customer newCustomer = Customer.create(customerParameter);

		// Print to console
		System.out.println("\nNew customer created!\n\tEmail: "+email+"\n\tID: "+newCustomer.getId()+"\n");
	}*/

	public static void printCustomerIdByEmail(String email) throws StripeException {
		// Get Customer ID from email

		Map<String, Object> options = new HashMap<>();
		options.put("email", email);
		List<Customer> customers = Customer.list(options).getData();

		if (customers.size() > 0) {
			Customer customer = customers.get(0);
			String cusId = customer.getId();
			System.out.println("Customer with email '" + email + "' exist!");
			System.out.println(cusId);
		} else {
			System.out.println("No customers with that email");
		}

		System.out.println("CUSTOMER INFO:\n"+customers);
	}

	// MOVED METHOD TO StripeService.java
	/*public static String getCustomerIdByEmail(String email) throws StripeException {
		// Get Customer ID from email

		Map<String, Object> options = new HashMap<>();
		options.put("email", email);
		List<Customer> customers = Customer.list(options).getData();
		System.out.println("CUSTOMERS:\n"+customers);

		if (customers.size() > 0) {
			Customer customer = customers.get(0);
			String cusId = customer.getId();
			System.out.println("Customer with email '" + email + "' is "+ cusId);
			return cusId;
		} else {
			System.out.println("No customers with that email");
			return "can't find customer ID";
		}
		//System.out.println(customers);
	}*/

	// MOVED METHOD TO StripeService.java
	/*public static void addPaymentFromEmailDefaultCard(String email, int amount, String currency, String description) throws StripeException {
		// use getCustomerIdByEmail() method to get Customer ID
		String cusId = getCustomerIdByEmail(email);

		// Retrieve correct customer
		Customer customer = Customer.retrieve(cusId);
		Map<String, Object> chargeParam = new HashMap<String, Object>();

		// Convert params to correct StripePayment params
		String amountString = Integer.toString(amount);
		String currencyToLower = currency.toLowerCase();

		// Add payment parameters
		chargeParam.put("amount", amountString); // 100 = 1.00 currency
		chargeParam.put("currency",currencyToLower); // for cents min 50
		chargeParam.put("description", description);
		chargeParam.put("customer",customer.getId()); // Using Customer ID (and get default card!)
		//chargeParam.put("source", "tok_mastercard");
		// ^ obtained with StripePayment.js

		// METADATA - Order_id
		Map<String, String> initialMetadata = new HashMap<String, String>();
		initialMetadata.put("order_id", "1234");
		chargeParam.put("metadata", initialMetadata);

		// Create Payment with parameters
		Charge charge = Charge.create(chargeParam);

		// Print to console
		System.out.println("Customer Payment for id "+ cusId +"is created!");
		gsonPrettyPrint(charge);
	}*/

	// MOVED METHOD TO StripeService.java
	/*public static void getCardIdFromLast4(String email, String last4) throws StripeException {
		// Get CardID from Last4
		Object cardId = null;

		Map<String, Object> options = new HashMap<>();
		options.put("email", email);
		List<Customer> customers = Customer.list(options).getData();
		System.out.println("ListEEEE " + customers);

		// IF CUSTOMER WITH EMAIL EXIST
		if (customers.size() > 0) {

			*//* ------- GET CUSTOMER ID ------------------------------------------ *//*
			Customer customer = customers.get(0);   // Get all info about that customer
			String cusId = customer.getId();   // Get customer ID from Customer
			System.out.println("Customer with email '" + email + "' is "+ cusId);   // Print Customer ID


			*//* ------- GET CARD ID ---------------------------------------------- *//*
			Object customerData = customer.getSources().getData();   // Get Data from Customer
			int dataSize = ((List) customerData).size();   // Save SIZE of data to use for loop

			boolean hasCardId = false;   // If there's a match, it sets this to true

			// Go through Customer Data
			for (int i = 0; i<dataSize; i++){
				// Save Part of Customer Data in variable
				Object customerDataPart = customer.getSources().getData().get(i);

				// Save to string and remove double quotes (to check if it contains "last4")
				String dataPartString = customerDataPart.toString().replace("\"", "").trim();

				// CHECK IF DATA PART CONTAINS last4 and prints it
				if (dataPartString.contains("last4: " + last4)){
					cardId = ((PaymentSource) customerDataPart).getId(); // If match, save Card ID
					//System.out.println("cus: "+"i:"+ i +gsonPrettyToJson(customerDataPart));
					System.out.println("cardID with last4 is "+last4+": " + cardId);
					hasCardId = true;   // Set to true
					break;
				}
			}
			// If no Card ID is found
			if (!hasCardId) {
				System.out.println("Customer has no card with last4 "+last4);
			}
		} else {
			System.out.println("No customers with that email");
		}

		System.out.println("THIS IS THE CARDID" + cardId);
	}*/

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

		// Convert params to correct StripePayment params
		String amountString = Integer.toString(amount);
		String currencyToLower = currency.toLowerCase();

		// Add payment parameters
		chargeParam.put("amount", amountString); // 100 = 1.00 currency
		chargeParam.put("currency",currencyToLower); // for cents min 50
		chargeParam.put("description", description);
		chargeParam.put("customer",customer.getId()); // Using Customer ID (and get default card!)
		//chargeParam.put("source", "tok_mastercard");
		// ^ obtained with StripePayment.js

		// METADATA - Order_id
		Map<String, String> initialMetadata = new HashMap<String, String>();
		initialMetadata.put("order_id", "1234");
		chargeParam.put("metadata", initialMetadata);

		// Create Payment with parameters
		Charge charge = Charge.create(chargeParam);

		// Print to console
		System.out.println("Customer Payment for id "+ cusId +"is created!");
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

		// Convert params to correct StripePayment params
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
		//System.out.println("Token: ");
		//gsonPrettyPrint(token);

	}

	public static void addPaymentWithToken(String token, int amount, String currency) throws StripeException {

		//Customer customer = Customer.retrieve(cusId);
		Map<String, Object> chargeParam = new HashMap<String, Object>();

		// Convert params to correct StripePayment params
		String amountString = Integer.toString(amount);
		String currencyToLower = currency.toLowerCase();

		// Add payment parameters
		//chargeParam.put("email", email); // 100 = 1.00 currency
		chargeParam.put("amount", amountString); // 100 = 1.00 currency
		chargeParam.put("currency",currencyToLower); // for cents min 50
		//chargeParam.put("customer",cusId);
		chargeParam.put("source",token); // Selecting card by card id

		// METADATA - Order_id
		Map<String, String> initialMetadata = new HashMap<String, String>();
		initialMetadata.put("order_id", "1234");
		chargeParam.put("metadata", initialMetadata);

		// Create Payment with parameters
		Charge charge = Charge.create(chargeParam);

		// Print to console
		System.out.println("Customer Payment is created with token: " + token);
		//System.out.println("Token: ");
		//gsonPrettyPrint(token);

	}

	// MOVED METHODS TO StripeService.java
	/**
	 * Use Gson to pretty-print Json
	 *
	 * @param obj from input
	public static void gsonPrettyPrint(Object obj) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(obj));
	}

	public static String gsonPrettyToJson(Object obj) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(obj);
	}*/

}
