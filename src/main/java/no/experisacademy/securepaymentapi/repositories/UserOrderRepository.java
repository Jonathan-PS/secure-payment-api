package no.experisacademy.securepaymentapi.repositories;

import no.experisacademy.securepaymentapi.models.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {
    List<UserOrder> findAll();
    List<UserOrder> findByRegisteredUserId(Long registeredUserId);

    @Query(value = "SELECT * FROM user_order ORDER BY user_order_id", nativeQuery = true)
    List<UserOrder> findAllOrderedById();


}
