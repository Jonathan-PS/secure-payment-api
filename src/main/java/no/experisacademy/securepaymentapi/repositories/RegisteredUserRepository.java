package no.experisacademy.securepaymentapi.repositories;

import no.experisacademy.securepaymentapi.models.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {
  List<RegisteredUser> findAll();

  @Query("select r from RegisteredUser r where r.email = :#{#registeredUser.email} AND r.password =:#{#registeredUser.password}")
  List<RegisteredUser> login(@Param("registeredUser") RegisteredUser registeredUser);
}
