package no.experisacademy.securepaymentapi.controllers;

import no.experisacademy.securepaymentapi.models.RegisteredUser;
import no.experisacademy.securepaymentapi.repositories.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

  /* Returns a user. Matched on the email-address which is the primary key in the DB
  @GetMapping("/user/{userID")
  public user findUserByUserId(@PathVariable Long userId) {
      for (user u : RegisteredUser.getUserId()) {
          if (u.getUserId() == (userId)) {
              return u;
          }
      }
      return null;
  }*/

  /* Method for creating a new user */
  @PutMapping("/createuser")
  public String create(@RequestBody RegisteredUser registeredUser) {
    repository.save(new RegisteredUser(registeredUser.getFirstName(), registeredUser.getLastName(), registeredUser.getPassword(), registeredUser.getEmail(), registeredUser.getActiveAddressId(), true));

      return "New user is created";
  }

  @GetMapping("/loginuser")
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
