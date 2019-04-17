package no.experisacademy.securepaymentapi.controllers;

import no.experisacademy.securepaymentapi.models.OrderProduct;
import no.experisacademy.securepaymentapi.repositories.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class OrderProductController {

    @Autowired
    OrderProductRepository repository;

    @GetMapping("/orderproducts")
    public List<OrderProduct> findAllProducts(){
        List<OrderProduct> orderProductControllers = repository.findAll();
        return orderProductControllers;
    }
}
