package no.experisacademy.securepaymentapi.controllers;

import no.experisacademy.securepaymentapi.models.UserOrder;
import no.experisacademy.securepaymentapi.repositories.UserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class UserOrderController {

    @Autowired
    UserOrderRepository repository;

    @GetMapping("/orders")
    public List<UserOrder> findAllOrders() {
        List<UserOrder> userOrders = repository.findAllOrderedById();

        return userOrders;
    }

    @PutMapping("/orders/create")
    public Long createOreder(@RequestBody UserOrder userOrder) {
        Date date = new Date();
        UserOrder newOrder = repository.save(new UserOrder(
                userOrder.getUserOrderId(),
                userOrder.getRegisteredUserId(),
                userOrder.getShippingName(),
                userOrder.getShippingAddress(),
                userOrder.getOrderEmail(),
                userOrder.getTotalPrice(),
                userOrder.getCurrency(),
                date,
                date,
                "in progress",
                true));

        return newOrder.getUserOrderId();
    }
    /* Returns a UserOrder by id*/
    @GetMapping("/orders/{userOrderId}")
    public UserOrder findUserOrderByUserOrderId(@PathVariable long userOrderId) {
        Optional<UserOrder> userOrder = repository.findById(userOrderId);
        try {
            return userOrder.get();
        } catch (Exception e) {
            System.out.println("User not found");
            return null;
        }
    }

    // LIST ALL ORDERS FOR A USER
    @GetMapping("/orders/users/{registeredUserId}")
    public List<UserOrder> findOrdersByRegisteredUserId(@PathVariable Long registeredUserId){
        List<UserOrder> userOrders = repository.findByRegisteredUserIdOrdered(registeredUserId);
        return userOrders;
    }
}
