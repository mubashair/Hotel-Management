package com.prog.Hotel_Management.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prog.Hotel_Management.DTO.BookingDTO;
import com.prog.Hotel_Management.Service.BookingService;

@RestController
@RequestMapping("/api/hotels")
public class BookingController {
	@Autowired
	private BookingService bookingService;
	@PostMapping("/booking")
	public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO){
		BookingDTO createdBooking = bookingService.createBooking(bookingDTO);
		return new ResponseEntity<BookingDTO>(createdBooking, HttpStatus.CREATED);
	}

}
