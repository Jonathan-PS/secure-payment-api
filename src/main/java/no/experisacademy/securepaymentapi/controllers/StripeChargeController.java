package no.experisacademy.securepaymentapi.controllers;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import no.experisacademy.securepaymentapi.models.StripeChargeRequest;
import no.experisacademy.securepaymentapi.repositories.StripeChargeRepository;
import no.experisacademy.securepaymentapi.services.StripeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class StripeChargeController {
    @Autowired
    StripeChargeRepository repository;

    @Autowired
    StripeService service;

    @Value("sk_test_5B0GI5Lt8GUHvvptHkURkfY000Xj6Tvvii")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }


    @PutMapping("/stripe/charge")
    public StripeChargeRequest chargeRequest(@RequestBody StripeChargeRequest stripeChargeRequest, Model model)
            throws StripeException {
        //gsonPrettyPrint(stripeChargeRequest);
        //stripeChargeRequest.setDescription("Example charge");
        //stripeChargeRequest.setCurrency(StripeChargeRequest.Currency.nok);
        Date date = new Date();

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", Integer.toString(stripeChargeRequest.getAmount()));
        chargeParams.put("currency", stripeChargeRequest.getCurrency());
        //chargeParams.put("customer", "");
        chargeParams.put("description", stripeChargeRequest.getDescription());
        chargeParams.put("source", stripeChargeRequest.getToken());
        Charge charge =  Charge.create(chargeParams);

        // Print to console
        System.out.println("Customer Payment is created!");
        service.gsonPrettyPrint(charge);

        repository.save(new StripeChargeRequest(
                stripeChargeRequest.getUserOrderId(),
                stripeChargeRequest.getCurrency(),
                stripeChargeRequest.getAmount(),
                stripeChargeRequest.getReceiptEmail(),
                stripeChargeRequest.getToken(),
                charge.getDescription(),
                date,
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

    /*@PutMapping("/stripe/charge")
    public StripeChargeRequest chargeRequest(@RequestBody StripeChargeRequest stripeChargeRequest, Model model)
            throws StripeException {

        Date date = new Date();
        String cardId = service.getCardIdFromLast4(stripeChargeRequest.getReceiptEmail(), stripeChargeRequest.getLast4());

        // User getCustomerIdByEmail() method to get Customer ID
        String cusId = service.getCustomerIdByEmail(stripeChargeRequest.getReceiptEmail());

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
        chargeParam.put("source", cardId);

        // METADATA - Order_id
        Map<String, String> initialMetadata = new HashMap<String, String>();
        initialMetadata.put("order_id", stripeChargeRequest.getUserOrderId().toString());
        chargeParam.put("metadata", initialMetadata);

        // Create Payment with parameters
        Charge charge =  Charge.create(chargeParam);

        // Print to console
        System.out.println("Customer Payment is created!");
        service.gsonPrettyPrint(charge);

        repository.save(new StripeChargeRequest(
                stripeChargeRequest.getUserOrderId(),
                stripeChargeRequest.getCurrency(),
                stripeChargeRequest.getAmount(),
                stripeChargeRequest.getReceiptEmail(),
                stripeChargeRequest.getToken(),
                charge.getDescription(),
                stripeChargeRequest.getLast4(),
                date,
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

    @GetMapping("/stripe")
    public List<StripeChargeRequest> findAll(){
        List<StripeChargeRequest> stripeChargeRequests = repository.findAll();

        return stripeChargeRequests;
    }
}