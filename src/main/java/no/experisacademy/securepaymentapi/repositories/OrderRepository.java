package no.experisacademy.securepaymentapi.repositories;

import no.experisacademy.securepaymentapi.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();
}
