package no.experisacademy.securepaymentapi.controllers;

import no.experisacademy.securepaymentapi.models.OrderProduct;
import no.experisacademy.securepaymentapi.repositories.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    // LIST ALL ORDERPRODUCTS FOR AN ORDER
    @GetMapping("/orderproducts/orders/{userOrderId}")
    public List<OrderProduct> findOrderProductByUserId(@PathVariable Long userOrderId){
        List<OrderProduct> orderProducts = repository.findByUserOrderId(userOrderId);
        return orderProducts;
    }

    @PutMapping("/orderproducts/create")
    public Long create(@RequestBody OrderProduct orderProduct){
        repository.save(new OrderProduct(
                orderProduct.getUserOrderId(),
                orderProduct.getProductId(),
                orderProduct.getPriceEach(),
                orderProduct.getQuantity(),
                true
                ));

        return orderProduct.getUserOrderId();
    }

    @PutMapping("/orderproducts/bulkcreate")
    public String create(@RequestBody List<OrderProduct> orderProducts){
        repository.saveAll(orderProducts);

        return "Order products created";
    }

    // UPDATES ORDERPRODUCT
}
