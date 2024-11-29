package com.prog.Hotel_Management.DTO;

public class HotelDTO {
    private String name;
    private String city;
    private String address;
    private float rating;
    private boolean isAvailable;

    // Constructor
    public HotelDTO() {}

    public HotelDTO(String name, String city, String address, float rating, boolean isAvailable) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.rating = rating;
        this.isAvailable = isAvailable;
    }
 
    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

	@Override
	public String toString() {
		return "HotelDTO [name=" + name + ", city=" + city + ", address=" + address + ", rating=" + rating
				+ ", isAvailable=" + isAvailable + "]";
	}
    
    
}
