package com.cakefactory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "account_details")
public class AccountDetailsEntity {

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public AccountDetailsEntity(String email, String password, String addressLine1, String addressLine2, String postcode) {
        this.email = email;
        this.password = password;
    }

    public AccountDetailsEntity() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
