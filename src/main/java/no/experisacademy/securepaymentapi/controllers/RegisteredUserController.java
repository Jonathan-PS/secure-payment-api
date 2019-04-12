package no.experisacademy.securepaymentapi.controllers;

import no.experisacademy.securepaymentapi.models.RegisteredUser;
import no.experisacademy.securepaymentapi.repositories.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class RegisteredUserController {

  @Autowired
  RegisteredUserRepository repository;

  /* Returns all users in the database */
  @GetMapping("/users")
  public List<RegisteredUser> findAllRegistedUsers() {
    List<RegisteredUser> registeredUsers = repository.findAll();

    return registeredUsers;
  }

  /* Returns a user. Matched on the email-address which is the primary key in the DB */
  @GetMapping("/users/{registeredUserId}")
  public RegisteredUser findUserByUserId(@PathVariable long registeredUserId) {
    Optional<RegisteredUser> registeredUser = repository.findById(registeredUserId);
    try {
      return registeredUser.get();
    } catch (Exception e) {
      System.out.println("User not found");
      return null;
    }
  }

  /* Method for creating a new user */
  @PutMapping("/users/create")
  public String create(@RequestBody RegisteredUser registeredUser) {
    repository.save(new RegisteredUser(registeredUser.getFirstName(), registeredUser.getLastName(), registeredUser.getPassword(), registeredUser.getEmail(), registeredUser.getActiveAddressId(), true));

      return "New user is created";
  }

  @GetMapping("/users/login")
  public boolean login(@RequestBody RegisteredUser registeredUser, HttpServletRequest req) {

    try {
      List<RegisteredUser> loggedInUser = repository.login(registeredUser);

      //Create session for user

      if(loggedInUser.size() > 0){
        req.getSession().setAttribute("loggedIn", true);
        return true;
      }


    }catch (Exception e){
      System.out.println("Failed");

    }
    return false;
  }



}
