package no.experisacademy.securepaymentapi.controllers;

import no.experisacademy.securepaymentapi.models.OrderProduct;
import no.experisacademy.securepaymentapi.repositories.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    // LIST ALL ORDERPRODUCTS FOR A ORDER
    @GetMapping("/orderproducts/orders/{userOrderId}")
    public List<OrderProduct> findOrderProductByUserId(@PathVariable Long userOrderId){
        List<OrderProduct> orderProducts = repository.findByUserOrderId(userOrderId);
        return orderProducts;
    }
}
