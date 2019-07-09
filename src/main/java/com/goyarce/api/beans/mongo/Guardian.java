package com.goyarce.api.beans.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "guardians")
public class Guardian {

    @Id
    private String id;

    @Field("FirstName")
    private String firstName;
    @Field("LastName")
    private String lastName;
    @Field("AddressStreet")
    private String addressStreet;
    @Field("AddressState")
    private String addressState;
    @Field("AddressCity")
    private String addressCity;
    @Field("AddressZip")
    private String addressZip;
    @Field("PhoneNumber")
    private String phoneNumber;
    @Field("Email")
    private String email;

    public Guardian(){};

    public Guardian(String firstName, String lastName, String addressStreet, String addressState, String addressCity, String addressZip, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressStreet = addressStreet;
        this.addressState = addressState;
        this.addressCity = addressCity;
        this.addressZip = addressZip;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressZip() {
        return addressZip;
    }

    public void setAddressZip(String addressZip) {
        this.addressZip = addressZip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return "Guardian: {" + getFirstName() + ", " + getLastName() + "}";
    }
}
