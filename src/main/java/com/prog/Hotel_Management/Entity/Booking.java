package com.prog.Hotel_Management.Entity;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
//The Booking entity represents each booking made by a guest.
public class Booking {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	//many to one relationship with Hotel entity
	//The @ManyToOne annotation establishes the relationship with the Hotel entity.
	@ManyToOne
	@JoinColumn(name = "hotel_id", nullable=false)
	private Hotel hotel;//reference to the hotel being booked
	
	 private String guestName; // Name of the guest making the booking
	 private Date checkInDate; // Check-in date
	 private Date checkOutDate; // Check-out date
	 private Integer roomsBooked; // Number of rooms booked
	 
	 //Getters and Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
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
