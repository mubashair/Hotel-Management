package com.prog.Hotel_Management.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
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
	public List<HotelDTO> getAllHotels(){
		return hotelService.getAllHotels();
	
	}
}
