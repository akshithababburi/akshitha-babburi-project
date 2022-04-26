package com.iit.oops.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Account {
    private LocalDate date_created;
    private String uid;
    private String name;
    private Address address;
    private String phone;
    private String picture;
    private boolean is_active;


    public Account(String uid, String name, Address address, String phone, String picture, boolean is_active, LocalDate date_created) {
        this.uid = uid;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.picture = picture;
        this.is_active = is_active;
        if (date_created == null || date_created.equals(""))
            this.date_created = LocalDate.now();
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
    public LocalDate getDate_created() {
        return date_created;
    }

    @Override
    public String toString() {
        return "Account{" +
                "" + uid + '\'' +
                "" + name + '\'' +
                "" + address.toString() +
                "" + phone + '\'' +
                "" + picture + '\'' +
                "" + is_active +
                "" + date_created +
                '}';
    }
}
