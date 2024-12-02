package com.prog.Hotel_Management.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prog.Hotel_Management.DTO.HotelDTO;
import com.prog.Hotel_Management.Service.HotelService;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
	
	private final HotelService hotelService;
	// Constructor for dependency injection
	public HotelController(HotelService hotelService) {
		this.hotelService = hotelService;
	}

	// Get All Hotels
	@GetMapping
	public ResponseEntity<List<HotelDTO>> getAllHotels(){
		List<HotelDTO> hotels = hotelService.getAllHotels();
		return new ResponseEntity<List<HotelDTO>>(hotels, HttpStatus.OK);
	
	}
	//Get Hotel by id
	@GetMapping("/{id}")
	public ResponseEntity<HotelDTO> getHotelById(@PathVariable Long id) {
		HotelDTO hotel = hotelService.getHotelById(id);
		//return ResponseEntity.ok(hotelDTO);//Returns 200 OK with the hotel details
		return new ResponseEntity<HotelDTO>(hotel, HttpStatus.OK);
		
	}
	//Create a new hotel
	@PostMapping
	public ResponseEntity<HotelDTO> createHotel(@RequestBody HotelDTO hotelDTO) {
		HotelDTO createdHotel = hotelService.createHotel(hotelDTO);
		return new ResponseEntity<HotelDTO>(createdHotel, HttpStatus.CREATED);
	}
	//Updating the existing hotel based on id
	@PutMapping("/{id}")
	public ResponseEntity<HotelDTO> updateHotel(@PathVariable Long id, @RequestBody HotelDTO hotelDTO) {
			HotelDTO updatedHotel = hotelService.updateHotel(hotelDTO, id);
			//return ResponseEntity.ok(updatedHotel);
			return new ResponseEntity<>(updatedHotel, HttpStatus.OK);
	}
		
}
