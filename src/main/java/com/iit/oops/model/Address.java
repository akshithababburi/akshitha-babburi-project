package com.iit.oops.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {
    private String street;
    private String zip;

    public Address() {
    }

    public Address(String street, String zip) {
        this.street = street;
        this.zip = zip;
    }

    @JsonProperty
    public String getStreet() {
        return street;
    }

    @JsonProperty
    public String getZip() {
        return zip;
    }
}
