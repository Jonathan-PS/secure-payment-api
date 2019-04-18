package no.experisacademy.securepaymentapi.controllers;

import no.experisacademy.securepaymentapi.models.UserOrder;
import no.experisacademy.securepaymentapi.repositories.UserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class UserOrderController {

    @Autowired
    UserOrderRepository repository;

    @GetMapping("/orders")
    public List<UserOrder> findAllOrders() {
        List<UserOrder> userOrders = repository.findAll();

        return userOrders;
    }

    @PutMapping("/orders/create")
    public String createOreder(@RequestBody UserOrder userOrder) {

        repository.save(new UserOrder(
                userOrder.getUserOrderId(),
                userOrder.getRegisteredUserId(),
                userOrder.getShippingName(),
                userOrder.getShippingAddress(),
                userOrder.getOrderEmail(),
                "in progress",
                true));

        return "UserOrder created";
    }

    // LIST ALL ORDERS FOR A USER
    @GetMapping("/orders/users/{registeredUserId}")
    public List<UserOrder> findOrdersByRegisteredUserId(@PathVariable Long registeredUserId){
        List<UserOrder> userOrders = repository.findByRegisteredUserId(registeredUserId);
        return userOrders;
    }
}
