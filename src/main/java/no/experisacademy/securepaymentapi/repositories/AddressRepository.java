package no.experisacademy.securepaymentapi.repositories;

import no.experisacademy.securepaymentapi.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAll();

    List<Address> findByRegisteredUserId(Integer registeredUserId);

    @Query(value = "SELECT * FROM address WHERE registered_user_id = :#{#registeredUserId} ORDER BY address_id", nativeQuery = true)
    List<Address> findByRegisteredUserIdOrdered(@Param("registeredUserId")Integer registeredUserId);

    @Query(value = "SELECT * FROM address ORDER BY address_id", nativeQuery = true)
    List<Address> findAllOrderedById();
}
