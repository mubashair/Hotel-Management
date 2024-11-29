package com.prog.Hotel_Management.Service;

import java.util.ArrayList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Hotel_Management.DTO.HotelDTO;
import com.prog.Hotel_Management.Entity.Hotel;
import com.prog.Hotel_Management.Repository.HotelRepository;

@Service
public class HotelService {
	@Autowired
	private HotelRepository hotelRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(HotelService.class);
	
	//Convert entity to dto 
	public HotelDTO converToDTO(Hotel hotel) {
		HotelDTO hotelDTO = new HotelDTO();
		hotelDTO.setName(hotel.getName());
		hotelDTO.setCity(hotel.getCity());
		hotelDTO.setAddress(hotel.getAddress());
		hotelDTO.setRating(hotel.getRating());
		hotelDTO.setAvailable(hotel.isAvailable());
		return hotelDTO;
	}
	//convert DTO to entity
	public Hotel convertToEntity(HotelDTO dto) {
		Hotel hotel = new Hotel();
		hotel.setName(dto.getName());
		hotel.setCity(dto.getCity());
		hotel.setAddress(dto.getAddress());
		hotel.setRating(dto.getRating());
		hotel.setAvailable(dto.isAvailable());
		return hotel;
	}
	//
	//Get All Hotels
	public List<HotelDTO> getAllHotels(){
		//Fetches all hotel entities from repo 
		List<Hotel> hotels = hotelRepository.findAll();
		//create an empty list to store the dtos
		List<HotelDTO> hotelDTOs = new ArrayList<>();
		 // Convert each Hotel entity to a DTO and add it to the list
		for(Hotel hotel : hotels) {
			hotelDTOs.add(converToDTO(hotel));
		}
		
		
		return hotelDTOs;
	}
	//create a new Hotel
	public HotelDTO createHotel(HotelDTO hotelDTO) {
		logger.info("Input DTO: {}", hotelDTO);
		//1-Convert DTO to entity
		Hotel hotel = convertToEntity(hotelDTO);
		logger.info("Converted entity: {}", hotel);
		//2-Save Entity to database
		Hotel savedHotel = hotelRepository.save(hotel);
		logger.info("Saved entity: {}", savedHotel);
		//3-Convert the saved entity back to DTO
		return converToDTO(savedHotel);
		
	}
	
}
