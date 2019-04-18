package no.experisacademy.securepaymentapi.controllers;

import no.experisacademy.securepaymentapi.models.RegisteredUser;
import no.experisacademy.securepaymentapi.repositories.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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

  /* Returns a user by id*/
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
    Date date = new Date();
    repository.save(new RegisteredUser(registeredUser.getFirstName(), registeredUser.getLastName(), registeredUser.getPassword(), registeredUser.getEmail(), date, true));

      return "New user is created";
  }

  /** Takes email and password from the client,
   * tries to log them in,
   * then create a Session and returns a login confirmation.
   *
   * @param registeredUser
   * @param req
   * @return
   */
  @GetMapping("/users/login")
  public boolean login(@RequestBody RegisteredUser registeredUser, HttpServletRequest req) {

    try {
      List<RegisteredUser> loggedInUser = repository.login(registeredUser);

      //Create session for user:
      if(loggedInUser.size() > 0){
        req.getSession().setAttribute("loggedIn", true);

        // return confirmation of successful login to the client
        return true;
      }


    }catch (Exception e){
      System.out.println("Failed");

    }
    // return confirmation of failed login to the client
    return false;
  }



}
