package no.experisacademy.securepaymentapi.repositories;

import no.experisacademy.securepaymentapi.models.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {
    List<UserOrder> findAll();
    List<UserOrder> findByRegisteredUserId(Long registeredUserId);

    @Query(value = "SELECT * FROM user_order ORDER BY user_order_id", nativeQuery = true)
    List<UserOrder> findAllOrderedById();

    @Query(value = "select * from user_order WHERE registered_user_id = :registeredUserId ORDER BY updated_at DESC", nativeQuery = true)
    List<UserOrder> findByRegisteredUserIdOrdered(@Param("registeredUserId") Long registeredUserId);




}
