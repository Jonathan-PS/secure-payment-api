package no.experisacademy.securepaymentapi.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "RegisteredUser")
public class RegisteredUser implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "registered_user_id")
  private long registeredUserId;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "password")
  private String password;

  @Column(name = "email")
  private String email;

  @Column(name = "created_at")
  private Date createdAt;

  @Column(name = "active_address_id")
  private int activeAddressId;

  @Column(name = "is_active")
  private Boolean isActive;

  public RegisteredUser() {
  }

  public RegisteredUser(String password, String email) {
    this.password = password;
    this.email = email;
  }

  public RegisteredUser(String firstName, String lastName, String password, String email, int activeAddressId, Boolean isActive) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = password;
    this.email = email;
    this.activeAddressId = activeAddressId;
    this.isActive = isActive;
  }

  public long getRegistedUserId() {
    return registeredUserId;
  }

  public void setUserId(long id) {
    this.registeredUserId = registeredUserId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName() {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName() {
    this.lastName = lastName;
  }

  public String getFullName() {
    return firstName + " " + lastName; // Bedre med getFirstName() + " " getLastName() ?
  }

  public String getPassword() {
    return password;
  }

  public void setPassword() {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail() {
    this.email = email;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt() {
    this.createdAt = createdAt;
  }

  public int getActiveAddressId() {
    return activeAddressId;
  }

  public void setActiveAddressId() {
    this.activeAddressId = activeAddressId;
  }

  public Boolean getIsActive() {
    return isActive;
  }

  public void setIsActive() {
    this.isActive = isActive;
  }
}
