package no.experisacademy.securepaymentapi.controllers;

import no.experisacademy.securepaymentapi.models.Address;
import no.experisacademy.securepaymentapi.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class AddressController {

  @Autowired
  AddressRepository repository;

  /* Returns all addresses in the database */
  @GetMapping("/addresses")
  public List<Address> findAllAddresses() {
    List<Address> addresses = repository.findAll();
    return addresses;
  }

  /* Returns a user. Matched on the email-address which is the primary key in the DB */
  @GetMapping("/addresses/{addressId}")
  public Address findAddressById(@PathVariable long addressId) {
    Optional<Address> address = repository.findById(addressId);
    try {
      return address.get();
    } catch (Exception e) {
      System.out.println("Address not found");
      return null;
    }
  }

  // LIST ALL ADDRESSES FOR A USER
  @GetMapping("/addresses/users/{registeredUserId}")
  public List<Address> findAddressByRegisteredUserId(@PathVariable Integer registeredUserId){
    List<Address> userAddresses = repository.findByRegisteredUserId(registeredUserId);
    return userAddresses;
  }

  /* Creates a new address */
  @PutMapping("/addresses/create")
  public String create(@RequestBody Address address) {
    repository.save(new Address(address.getRegisteredUserId(), address.getStreetName(), address.getStreetNumber(), address.getHousingCode(), address.getCity(), address.getPostalCode(), address.getCountry(), true, true));

    return "New address created";
  }

  // UPDATES CURRENT ADDRESS

}
