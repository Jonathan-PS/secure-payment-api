package no.experisacademy.securepaymentapi.repositories;

import no.experisacademy.securepaymentapi.models.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {

  List<RegisteredUser> findAll();

  @Query("select r from RegisteredUser r where r.email = :#{#registeredUser.email} AND r.password = crypt(:#{#registeredUser.password}, r.password)")
  List<RegisteredUser> login(@Param("registeredUser") RegisteredUser registeredUser);

  // Inserts users info in the database with encrypted password
  @Modifying
  @Transactional
  @Query(value = "INSERT INTO registered_user (email, first_name, last_name, password, created_at, is_active) VALUES (:#{#registeredUser.email}, :#{#registeredUser.firstName}, :#{#registeredUser.lastName}, crypt(:#{#registeredUser.password}, gen_salt('md5')), NOW(), true);", nativeQuery = true)
  void registerUser(@Param("registeredUser") RegisteredUser registeredUser);


}
