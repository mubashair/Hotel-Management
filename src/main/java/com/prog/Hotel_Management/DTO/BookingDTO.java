package com.prog.Hotel_Management.DTO;

import java.util.Date;

public class BookingDTO {
	private Long id;
    private Long hotelId; // Reference to the ID of the hotel being booked
    private String guestName; // Name of the guest making the booking
    private Date checkInDate; // Check-in date
    private Date checkOutDate; // Check-out date
    private Integer roomsBooked; // Number of rooms booked
    
    private String hotelName; // New field for hotel name
    private String city; // New field for hotel city
    
    
    //Getters and Setters
	public Long getId() {
		return id;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getHotelId() {
		return hotelId;
	}
	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public Date getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}
	public Date getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public Integer getRoomsBooked() {
		return roomsBooked;
	}
	public void setRoomsBooked(Integer roomsBooked) {
		this.roomsBooked = roomsBooked;
	}
    
    

}
