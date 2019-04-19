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
    List<Address> addresses = repository.findAllOrderedById();
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
    List<Address> userAddresses = repository.findByRegisteredUserIdOrdered(registeredUserId);
    return userAddresses;
  }

  /* Creates a new address */
  @PutMapping("/addresses/create")
  public String create(@RequestBody Address address) {
    List<Address> userAddresses = repository.findByRegisteredUserId(address.getRegisteredUserId());
    for(Address a : userAddresses){
      if(a.getCurrent().equals(true)){
        a.setCurrent(false);
        repository.save(a);
      }
    }
    repository.save(new Address(address.getRegisteredUserId(), address.getStreetName(), address.getStreetNumber(), address.getHousingCode(), address.getCity(), address.getPostalCode(), address.getCountry(), true, true));

    return "New address created";
  }

  // UPDATES CURRENT ADDRESS
  @PutMapping("addresses/updatecurrent/{addressId}")
  public String updateCurrent(@PathVariable Long addressId){
    Address newCurrentAddress = repository.findById(addressId).get();

    List<Address> userAddresses = repository.findByRegisteredUserId(newCurrentAddress.getRegisteredUserId());

    for(Address address : userAddresses) {
      if (address.getCurrent().equals(true)) {
        address.setCurrent(false);
        repository.save(address);
      }
    }

    newCurrentAddress.setCurrent(true);
    repository.save(newCurrentAddress);


    return "Current address updated";
  }

}
