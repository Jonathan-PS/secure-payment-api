package no.experisacademy.securepaymentapi.repositories;

import no.experisacademy.securepaymentapi.models.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {
    List<UserOrder> findAll();
    List<UserOrder> findByRegisteredUserId(Long registeredUserId);

}
