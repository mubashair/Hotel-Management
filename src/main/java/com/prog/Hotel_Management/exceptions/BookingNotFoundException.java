package com.prog.Hotel_Management.exceptions;
//Extend RuntimeException to make it an unchecked exception
public class BookingNotFoundException extends RuntimeException {
	// Constructor to accept a custom error message
	public BookingNotFoundException(String message) {
		super(message);
		
	}
	

}
