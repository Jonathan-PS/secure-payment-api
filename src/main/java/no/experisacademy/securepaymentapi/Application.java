package no.experisacademy.securepaymentapi;

import no.experisacademy.securepaymentapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	@Autowired
	ProductRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
