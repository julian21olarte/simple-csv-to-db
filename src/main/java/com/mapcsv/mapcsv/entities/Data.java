package com.mapcsv.mapcsv.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Data {
    private int id;
    private String city;
    private String name;
    private String streetAddress;
    private String locality;
    private String addressRegion;
    private String postalCode;
    private String phone;
    private String website;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "city", nullable = true, length = 50)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 80)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "street_address", nullable = true, length = 50)
    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    @Basic
    @Column(name = "locality", nullable = true, length = 80)
    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    @Basic
    @Column(name = "address_region", nullable = true, length = 10)
    public String getAddressRegion() {
        return addressRegion;
    }

    public void setAddressRegion(String addressRegion) {
        this.addressRegion = addressRegion;
    }

    @Basic
    @Column(name = "postal_code", nullable = true, length = 10)
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 20)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "website", nullable = true, length = 200)
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return id == data.id &&
                Objects.equals(city, data.city) &&
                Objects.equals(name, data.name) &&
                Objects.equals(streetAddress, data.streetAddress) &&
                Objects.equals(locality, data.locality) &&
                Objects.equals(addressRegion, data.addressRegion) &&
                Objects.equals(postalCode, data.postalCode) &&
                Objects.equals(phone, data.phone) &&
                Objects.equals(website, data.website);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, city, name, streetAddress, locality, addressRegion, postalCode, phone, website);
    }
}
