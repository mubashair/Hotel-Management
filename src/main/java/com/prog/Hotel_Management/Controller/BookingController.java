package com.prog.Hotel_Management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	//Get all bookings
	@GetMapping("/bookings")
	public ResponseEntity<?> getAllBookings(){
		try {
			List<BookingDTO> bookings = bookingService.getAllBookings();
			return ResponseEntity.ok(bookings); // Return 200 OK with bookings
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
	}//end get all bookings
	//Bookings by guest name
	@GetMapping("/bookings/by-guest-name")
	public ResponseEntity<List<BookingDTO>> getBookingsByGuestName(@RequestParam String guestName){
		List<BookingDTO> bookingsByGuestName = bookingService.findBookingsByGuestName(guestName);
		return ResponseEntity.ok(bookingsByGuestName);
	}
}
