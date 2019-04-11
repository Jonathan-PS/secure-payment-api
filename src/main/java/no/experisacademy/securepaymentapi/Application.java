package no.experisacademy.securepaymentapi;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import no.experisacademy.securepaymentapi.repositories.ProductRepository;
import no.experisacademy.securepaymentapi.repositories.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application {

	/*@Autowired
	ProductRepository repository;*/
	public static void main(String[] args) throws StripeException{
		Stripe.apiKey="sk_test_5B0GI5Lt8GUHvvptHkURkfY000Xj6Tvvii";

		//getAllCustomers();

		SpringApplication.run(Application.class, args);



		/* New customer
		Map<String, Object> customerParameter = new HashMap<String, Object>();
		customerParameter.put("email", "ola@normann.com");
		Customer newCustomer = Customer.create(customerParameter);
		System.out.println(newCustomer.getProductId());
		*/


	}

	public static void getAllCustomers() throws StripeException {
		Customer getCustomer = Customer.retrieve("cus_ErLVczAX1v7jpH");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println("Customer retrieve:\n"+gson.toJson(getCustomer));

	}

	public static void createCustomer(String email) throws StripeException {
		Map<String, Object> customerParameter = new HashMap<String, Object>();
		customerParameter.put("email", email); // ("email", "ola@normann.com")
		Customer newCustomer = Customer.create(customerParameter);
		System.out.println(newCustomer.getId());
	}

}
