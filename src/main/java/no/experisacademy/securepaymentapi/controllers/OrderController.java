package no.experisacademy.securepaymentapi.controllers;

import no.experisacademy.securepaymentapi.models.Order;
import no.experisacademy.securepaymentapi.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderRepository repository;

    @GetMapping("/orders")
    public List<Order> findAllOrders() {
        List<Order> orders = repository.findAll();

        return orders;
    }

    @PostMapping("/createOrder")
    public String createOreder(@RequestBody Order order) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();


        repository.save(new Order(
                order.getUserOrderId(),
                order.getRegisteredUserId(),
                order.getShippingName(),
                order.getShippingAddress(),
                order.getShippingEmail(),
                dateFormat.format(date),
                date,
                "in progress",
                true));

        return "Order created";
    }
}
