package no.experisacademy.securepaymentapi.services;

import com.stripe.Stripe;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import no.experisacademy.securepaymentapi.models.ChargeRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    @Value("sk_test_5B0GI5Lt8GUHvvptHkURkfY000Xj6Tvvii")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public Charge charge(ChargeRequest chargeRequest)
            throws AuthenticationException, InvalidRequestException,
            /*APIConnectionException,*/ CardException, /*APIException,*/ StripeException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", Integer.toString(chargeRequest.getAmount()));
        chargeParams.put("currency", chargeRequest.getCurrency());
        //chargeParams.put("email", chargeRequest.getStripeEmail());
        //chargeParams.put("description", chargeRequest.getDescription());
        chargeParams.put("source", chargeRequest.getStripeToken());
        return Charge.create(chargeParams);
    }
}