package no.experisacademy.securepaymentapi.controller;

import no.experisacademy.securepaymentapi.models.Address;
import no.experisacademy.securepaymentapi.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

  /* Creates a new address */
  @PutMapping("/addresses/create")
  public String create(@RequestBody Address address) {
    repository.save(new Address(address.getRegisterdUserId(), address.getStreetName(), address.getStreetNumber(), address.getHousingCode(), address.getCity(), address.getPostalCode(), address.getCountry(), true));

    return "New address created";
  }
}
