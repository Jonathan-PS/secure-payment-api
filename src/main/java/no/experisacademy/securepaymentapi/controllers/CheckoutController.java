/*
package no.experisacademy.securepaymentapi.controllers;

import no.experisacademy.securepaymentapi.models.Checkout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CheckoutController {
 
    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;
 
    @RequestMapping("/checkout")
    public String checkout(Model model, Object ChargeRequest) {
        model.addAttribute("amount", 50 * 100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        //model.addAttribute("currency", ChargeRequest.Currency.EUR);
        return "checkout";
    }
}*/
