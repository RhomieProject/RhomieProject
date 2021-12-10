package com.example.rhomie.Objects;


import java.util.HashMap;

public class Address {

    private String city;
    private String street;
    private int street_number;
    private int floor;
    private int apartment_number;

    /* Default Constructor */
    public Address () {
        city = "";
        street = "";
        street_number = 0;
        floor = 0;
        apartment_number = 0;
    }

    /* Full Constructor */
    public Address (String c,String s,int sn,int f, int an) {
        this.city = c;
        this.street = s;
        this.street_number = sn;
        this.floor = f;
        this.apartment_number = an;
    }

    /* City */
    public String getCity () {
        return this.city;
    }

    public void setCity (String c) {
        this.city = c;
    }

    /* Street */
    public String getStreet () {
        return this.street;
    }

    public void setStreet (String s) {
        this.street = s;
    }

    /* Street Number */
    public int getStreetNumber () {
        return this.street_number;
    }

    public void setStreetNumber (int sn) {
        this.street_number = sn;
    }

    /* Floor */
    public int getFloor () {
        return this.floor;
    }

    public void setFloor (int f) {
        this.floor = f;
    }

    /* Apartment Number */
    public int getApartmentNumber () {
        return this.apartment_number;
    }

    public void setApartmentNumber (int an) {
        this.apartment_number = an;
    }

    public HashMap<String,Object> addressToMap () {
        HashMap<String, Object> address = new HashMap<>();
        address.put("city", this.city);
        address.put("street", this.street);
        address.put("street_number", this.street_number);
        address.put("floor", this.floor);
        address.put("apartment_number", this.apartment_number);
        return address;
    }
}