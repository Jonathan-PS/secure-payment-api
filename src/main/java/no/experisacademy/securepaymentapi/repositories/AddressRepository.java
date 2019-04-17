package no.experisacademy.securepaymentapi.repositories;

import no.experisacademy.securepaymentapi.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAll();

    List<Address> findByRegisteredUserId(Integer registeredUserId);
}
