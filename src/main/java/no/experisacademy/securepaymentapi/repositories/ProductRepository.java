package no.experisacademy.securepaymentapi.repositories;

import no.experisacademy.securepaymentapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();
}
