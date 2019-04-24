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

  @Query(value = "INSERT INTO registered_user (email, first_name, last_name, password, created_at, is_active) VALUES (:#{#registeredUser.email}, :#{#registeredUser.firstName}, :#{#registeredUser.lastName}, crypt(:#{#registeredUser.password}, gen_salt('md5')), NOW(), true);", nativeQuery = true)
  RegisteredUser registerUser(@Param("registeredUser") RegisteredUser registeredUser);
}
