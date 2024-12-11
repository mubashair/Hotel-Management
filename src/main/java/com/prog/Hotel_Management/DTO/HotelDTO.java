package com.prog.Hotel_Management.DTO;

public class HotelDTO {
    private String name;
    private String city;
    private String address;
    private float rating;
    private Boolean isAvailable;
    private Integer totalRooms;//Total rooms in hotel
    private Integer availableRooms;//Rooms currently available for booking

    // Constructor
    public HotelDTO() {}

    
 
    public HotelDTO(String name, String city, String address, float rating, Boolean isAvailable, Integer totalRooms,
			Integer availableRooms) {
		
		this.name = name;
		this.city = city;
		this.address = address;
		this.rating = rating;
		this.isAvailable = isAvailable;
		this.totalRooms = totalRooms;
		this.availableRooms = availableRooms;
	}



	public Integer getTotalRooms() {
		return totalRooms;
	}

	public void setTotalRooms(Integer totalRooms) {
		this.totalRooms = totalRooms;
	}

	public Integer getAvailableRooms() {
		return availableRooms;
	}

	public void setAvailableRooms(Integer availableRooms) {
		this.availableRooms = availableRooms;
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

    public Boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

	@Override
	public String toString() {
		return "HotelDTO [name=" + name + ", city=" + city + ", address=" + address + ", rating=" + rating
				+ ", isAvailable=" + isAvailable + "]";
	}
    
    
}
