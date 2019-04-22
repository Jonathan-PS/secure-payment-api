package no.experisacademy.securepaymentapi.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import no.experisacademy.securepaymentapi.models.StripeChargeRequest;
import no.experisacademy.securepaymentapi.repositories.StripeChargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class StripeChargeController {
    @Autowired
    StripeChargeRepository repository;

    @Value("sk_test_5B0GI5Lt8GUHvvptHkURkfY000Xj6Tvvii")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }


    /*@PutMapping("/stripe/charge")
    public StripeChargeRequest chargeRequest(@RequestBody StripeChargeRequest stripeChargeRequest, Model model)
            throws StripeException {
        //gsonPrettyPrint(stripeChargeRequest);
        //stripeChargeRequest.setDescription("Example charge");
        //stripeChargeRequest.setCurrency(StripeChargeRequest.Currency.nok);

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", Integer.toString(stripeChargeRequest.getAmount()));
        chargeParams.put("currency", stripeChargeRequest.getCurrency());
        //chargeParams.put("customer", "");
        chargeParams.put("description", stripeChargeRequest.getDescription());
        chargeParams.put("source", stripeChargeRequest.getToken());
        Charge charge =  Charge.create(chargeParams);

        // Print to console
        System.out.println("Customer Payment is created!");
        gsonPrettyPrint(charge);

        repository.save(new StripeChargeRequest(
                stripeChargeRequest.getUserOrderId(),
                stripeChargeRequest.getCurrency(),
                stripeChargeRequest.getAmount(),
                stripeChargeRequest.getReceiptEmail(),
                stripeChargeRequest.getToken(),
                charge.getDescription(),
                charge.getId(),
                charge.getReceiptUrl(),
                charge.getStatus(),
                charge.getPaid(),
                charge.getOutcome().getNetworkStatus(),
                charge.getOutcome().getRiskLevel(),
                charge.getOutcome().getRiskScore(),
                charge.getOutcome().getSellerMessage(),
                charge.getOutcome().getType(),
                true
                ));

        return stripeChargeRequest;
    }*/

    @PutMapping("/stripe/charge")
    public StripeChargeRequest chargeRequest(@RequestBody StripeChargeRequest stripeChargeRequest, Model model)
            throws StripeException {

        // User getCustumerIdByEmail() method to get Customer ID
        String cusId = getCustomerIdByEmail(stripeChargeRequest.getReceiptEmail());

        // Retrieve correct customer
        Customer customer = Customer.retrieve(cusId);
        Map<String, Object> chargeParam = new HashMap<String, Object>();

        // Convert params to correct StripePayment params
        String amountString = Integer.toString(stripeChargeRequest.getAmount());
        String currencyToLower = stripeChargeRequest.getCurrency().toLowerCase();

        // Add payment parameters
        chargeParam.put("amount", amountString); // 100 = 1.00 currency
        chargeParam.put("currency", currencyToLower); // for cencts min 50
        chargeParam.put("description", stripeChargeRequest.getDescription());
        chargeParam.put("customer", customer.getId());
        chargeParam.put("payment_method", stripeChargeRequest.getToken());

        // METADATA - Order_id
        Map<String, String> initialMetadata = new HashMap<String, String>();
        initialMetadata.put("order_id", stripeChargeRequest.getUserOrderId().toString());
        chargeParam.put("metadata", initialMetadata);

        // Create Payment with parameters
        Charge charge =  Charge.create(chargeParam);

        // Print to console
        System.out.println("Customer Payment is created!");
        gsonPrettyPrint(charge);

        repository.save(new StripeChargeRequest(
                stripeChargeRequest.getUserOrderId(),
                stripeChargeRequest.getCurrency(),
                stripeChargeRequest.getAmount(),
                stripeChargeRequest.getReceiptEmail(),
                stripeChargeRequest.getToken(),
                charge.getDescription(),
                charge.getId(),
                charge.getReceiptUrl(),
                charge.getStatus(),
                charge.getPaid(),
                charge.getOutcome().getNetworkStatus(),
                charge.getOutcome().getRiskLevel(),
                charge.getOutcome().getRiskScore(),
                charge.getOutcome().getSellerMessage(),
                charge.getOutcome().getType(),
                true
        ));

        return stripeChargeRequest;
    }

    @GetMapping("/stripe")
    public List<StripeChargeRequest> findAll(){
        List<StripeChargeRequest> stripeChargeRequests = repository.findAll();

        return stripeChargeRequests;
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

}