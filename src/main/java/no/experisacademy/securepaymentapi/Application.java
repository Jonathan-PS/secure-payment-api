package no.experisacademy.securepaymentapi;


import com.stripe.exception.StripeException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {

	public static void main(String[] args) throws StripeException{
		SpringApplication.run(Application.class, args);
	}
}
