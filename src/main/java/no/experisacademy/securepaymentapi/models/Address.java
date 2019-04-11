package no.experisacademy.securepaymentapi.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private long addressId;

    @Column(name = "registered_user_id")
    private int registeredUserId;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "street_number")
    private String streetNumber;

    @Column(name = "housing_code")
    private String housingCode;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private int postalCode;

    @Column(name = "country")
    private String country;

    @Column(name = "is_active")
    private Boolean isActive;

    public Address() {
    }

    public Address(int registeredUserId, String streetName, String streetNumber, String housingCode, String city, int postalCode, String country, Boolean isActive) {
        this.registeredUserId = registeredUserId;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.housingCode = housingCode;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.isActive = isActive;
    }

    public long getAddressId() {
        return addressId;
    }

    public int getRegisterdUserId() {
        return registeredUserId;
    }

    public void setRegisteredUserId() {
        this.registeredUserId = registeredUserId;
    }

    public String getStreetName() {
        return  streetName;
    }

    public void setStreetName() {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber() {
        this.streetNumber = streetNumber;
    }

    public String getHousingCode() {
        return housingCode;
    }

    public void setHousingCode() {
        this.housingCode = housingCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode() {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry() {
        this.country = country;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive() {
        this.isActive = isActive;
    }
}
