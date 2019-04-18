package no.experisacademy.securepaymentapi.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
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


    @PutMapping("/stripe/charge")
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
        System.out.println("SE HER" + charge.getId());

        repository.save(new StripeChargeRequest(
                stripeChargeRequest.getUserOrderId(),
                stripeChargeRequest.getCurrency(),
                stripeChargeRequest.getAmount(),
                stripeChargeRequest.getReceiptEmail(),
                stripeChargeRequest.getToken(),
                charge.getReceiptUrl(),
                charge.getStatus(),
                charge.getDescription(),
                charge.getId(),
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

}