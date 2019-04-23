package no.experisacademy.securepaymentapi.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.PaymentSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StripeService {

    @Value("sk_test_5B0GI5Lt8GUHvvptHkURkfY000Xj6Tvvii")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public static String getCustomerIdByEmail(String email) throws StripeException {
        // Get Customer ID from email

        Map<String, Object> options = new HashMap<>();
        options.put("email", email);
        List<Customer> customers = Customer.list(options).getData();

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
    }

    public static void addPaymentFromEmailDefaultCard(String email, int amount, String currency, String description) throws StripeException {
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
    }

    public static String getCardIdFromLast4(String email, String last4) throws StripeException {
        // Get CardID from Last4
        Object cardId = null;

        Map<String, Object> options = new HashMap<>();
        options.put("email", email);

        // Creates a list with the customer information
        List<Customer> customers = Customer.list(options).getData();

        // IF CUSTOMER WITH EMAIL EXIST
        if (customers.size() > 0) {

            /* ------- GET CUSTOMER ID ------------------------------------------ */
            Customer customer = customers.get(0);   // Get all info about that customer
            String cusId = customer.getId();   // Get customer ID from Customer
            System.out.println("Customer with email '" + email + "' is "+ cusId);   // Print Customer ID


            /* ------- GET CARD ID ---------------------------------------------- */
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

                return "Customer has no card with last4 " + last4;
            }
        } else {
            System.out.println("No customers with that email");
        }

        return cardId.toString();
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