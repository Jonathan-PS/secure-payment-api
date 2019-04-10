package no.experisacademy.securepaymentapi.controllers;

import no.experisacademy.securepaymentapi.models.RegisteredUser;
import no.experisacademy.securepaymentapi.repositories.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegisteredUserController {

  @Autowired
  RegisteredUserRepository repository;

  @GetMapping("/users")
  public List<RegisteredUser> findAllRegistedUsers() {
    List<RegisteredUser> registeredUsers = repository.findAll();

    return registeredUsers;
  }

  @PutMapping("/createuser")
  public String create(@RequestBody RegisteredUser registeredUser) {
    repository.save(new RegisteredUser(registeredUser.getFullName(), registeredUser.getEmail(),
      registeredUser.getPassword(), registeredUser.getActiveAddressId(), registeredUser.getIsActive(),
      registeredUser.getCreatedAt(), registeredUser.getRegistedUserId(), true));

      return "New user is created";
  }
}
