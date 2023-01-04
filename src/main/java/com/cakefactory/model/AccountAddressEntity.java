package com.cakefactory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "account_address")
public class AccountAddressEntity {

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "address_line_1")
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    @Column(name = "postcode")
    private String postcode;

    public AccountAddressEntity() {
    }

    public AccountAddressEntity(String email, String addressLine1, String addressLine2, String postcode) {
        this.email = email;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.postcode = postcode;
    }

    public String getEmail() {
        return email;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getPostcode() {
        return postcode;
    }
}
