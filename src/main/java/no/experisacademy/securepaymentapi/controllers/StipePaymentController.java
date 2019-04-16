package no.experisacademy.securepaymentapi.controllers;

import no.experisacademy.securepaymentapi.models.StripePayment;
import no.experisacademy.securepaymentapi.repositories.StripePaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class StipePaymentController {

    @Autowired
    StripePaymentRepository repository;

    @GetMapping("/stripe")
    public List<StripePayment> findAllStripePayments(){
        List<StripePayment> stripePayments = repository.findAll();

        return stripePayments;
    }

    @PutMapping("/stripe/payment")
    public String create(@RequestBody StripePayment stripePayment){
        repository.save(new StripePayment(stripePayment.getStripeTransactionId(), stripePayment.getTokenId(), stripePayment.getEmail(), stripePayment.getAmount(), stripePayment.getCurrency()));

        return "Stripe payment has been created";
    }
}
