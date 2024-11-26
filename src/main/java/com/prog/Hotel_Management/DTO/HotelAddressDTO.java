package com.prog.Hotel_Management.DTO;

public class HotelAddressDTO {
    private String city;
    private String address;

    // Constructor
    public HotelAddressDTO() {}

    public HotelAddressDTO(String city, String address) {
        this.city = city;
        this.address = address;
    }

    // Getters and Setters
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "HotelAddressDTO{" +
                "city='" + city + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
