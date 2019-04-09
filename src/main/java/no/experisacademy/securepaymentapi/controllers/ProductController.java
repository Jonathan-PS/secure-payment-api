package no.experisacademy.securepaymentapi.controllers;

import no.experisacademy.securepaymentapi.models.Product;
import no.experisacademy.securepaymentapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductRepository repository;

    @GetMapping("/products")
    public List<Product> findAllProducts(){
        List<Product> customers = repository.findAll();

        return customers;
    }

    @PostMapping("/createProduct")
    public String create(@RequestBody Product product){
        repository.save(new Product(product.getProductName(), product.getDescription(), product.getPriceEach(), product.getQuantity(), product.getImageURL(), true));

        return "Product is created";
    }
}
