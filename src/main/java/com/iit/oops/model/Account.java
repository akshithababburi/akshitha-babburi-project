package com.iit.oops.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Account {
    private final LocalDateTime date_created = LocalDateTime.now();
    private String uid;
    private String name;
    private Address address;
    private String phone;
    private String picture;
    private boolean is_active;


    public Account(String uid, String name, Address address, String phone, String picture, boolean is_active, LocalDateTime date_created) {
        this.uid = uid;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.picture = picture;
        this.is_active = is_active;
    }

    public Account() {
    }


    @JsonProperty
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public Address getAddress() {
        return address;
    }

    @JsonProperty
    public String getPhone() {
        return phone;
    }

    @JsonProperty
    public String getPicture() {
        return picture;
    }

    @JsonProperty
    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    @JsonProperty
    public LocalDateTime getDate_created() {
        return date_created;
    }

    @Override
    public String toString() {
        return "Account{" +
                "" + uid + '\'' +
                "" + name + '\'' +
                "" + address +
                "" + phone + '\'' +
                "" + picture + '\'' +
                "" + is_active +
                "" + date_created +
                '}';
    }
}
