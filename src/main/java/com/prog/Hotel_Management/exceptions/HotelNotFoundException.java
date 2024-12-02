package com.prog.Hotel_Management.exceptions;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class HotelNotFoundException extends RuntimeException {

	public HotelNotFoundException(String message) {
		super(message);
		
	}
	
}
