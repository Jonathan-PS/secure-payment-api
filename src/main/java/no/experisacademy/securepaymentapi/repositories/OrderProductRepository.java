package no.experisacademy.securepaymentapi.repositories;

import no.experisacademy.securepaymentapi.models.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    List<OrderProduct> findAll();
    List<OrderProduct> findByUserOrderId(Long userOrderId);
}
