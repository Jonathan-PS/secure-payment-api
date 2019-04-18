package no.experisacademy.securepaymentapi.services;

import com.stripe.Stripe;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import no.experisacademy.securepaymentapi.models.StripeChargeRequest;
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

    public Charge charge(StripeChargeRequest stripeChargeRequest)
            throws AuthenticationException, InvalidRequestException,
            /*APIConnectionException,*/ CardException, /*APIException,*/ StripeException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", Double.toString(stripeChargeRequest.getAmount()));
        chargeParams.put("currency", stripeChargeRequest.getCurrency());
        //chargeParams.put("email", stripeChargeRequest.getStripeEmail());
        //chargeParams.put("description", stripeChargeRequest.getDescription());
        chargeParams.put("source", stripeChargeRequest.getToken());
        return Charge.create(chargeParams);
    }
}