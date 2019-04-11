package no.experisacademy.securepaymentapi.repositories;

import no.experisacademy.securepaymentapi.models.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {
  List<RegisteredUser> findAll();
}
