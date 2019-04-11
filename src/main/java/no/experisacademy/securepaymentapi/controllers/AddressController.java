package no.experisacademy.securepaymentapi.controller;

import no.experisacademy.securepaymentapi.models.Address;
import no.experisacademy.securepaymentapi.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

  /* Creates a new address */
  @PutMapping("/createaddress")
  public String create(@RequestBody Address address) {
    repository.save(new Address(address.getRegisterdUserId(), address.getStreetName(), address.getStreetNumber(), address.getHousingCode(), address.getCity(), address.getPostalCode(), address.getCountry(), true));

    return "New address created";
  }
}
