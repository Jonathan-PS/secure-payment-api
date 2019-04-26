package no.experisacademy.securepaymentapi.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "registered_user")
public class RegisteredUser implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "serial")
  private Long registeredUserId;

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

  @Column(name = "is_active")
  private Boolean isActive;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "registered_user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;

  public RegisteredUser() {
  }

  public RegisteredUser(String password, String email) {
    this.password = password;
    this.email = email;
  }

  public RegisteredUser(RegisteredUser registeredUser){
    this.registeredUserId = registeredUser.getRegisteredUserId();
    this.firstName = registeredUser.getFirstName();
    this.lastName = registeredUser.getLastName();
    this.password = registeredUser.getPassword();
    this.email = registeredUser.getEmail();
    this.createdAt = registeredUser.getCreatedAt();
    this.isActive = registeredUser.getActive();
    this.roles = registeredUser.getRoles();

  }

  public RegisteredUser(String firstName, String lastName, String password, String email, Date createdAt, Boolean isActive) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = password;
    this.email = email;
    this.createdAt = createdAt;
    this.isActive = isActive;
  }

  public Long getRegisteredUserId() {
    return registeredUserId;
  }

  public void setRegisteredUserId(Long registeredUserId) {
    this.registeredUserId = registeredUserId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Boolean getActive() {
    return isActive;
  }

  public void setActive(Boolean active) {
    isActive = active;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}
