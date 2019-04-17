package no.experisacademy.securepaymentapi.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import no.experisacademy.securepaymentapi.models.ChargeRequest;
import no.experisacademy.securepaymentapi.services.StripeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class ChargeController {
    @Value("sk_test_5B0GI5Lt8GUHvvptHkURkfY000Xj6Tvvii")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }


    @PostMapping("/stripe/charge")
    public Charge charge(@RequestBody ChargeRequest chargeRequest, Model model)
            throws StripeException {
        //gsonPrettyPrint(chargeRequest);
        //chargeRequest.setDescription("Example charge");
        //chargeRequest.setCurrency(ChargeRequest.Currency.nok);

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", Integer.toString(chargeRequest.getAmount()));
        chargeParams.put("currency", chargeRequest.getCurrency());
        //chargeParams.put("customer", "");
        chargeParams.put("description", chargeRequest.getDescription());
        chargeParams.put("source", chargeRequest.getToken());
        Charge charge =  Charge.create(chargeParams);

        // Print to console
        System.out.println("Customer Payment is created!");
        gsonPrettyPrint(charge);

        return charge;
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