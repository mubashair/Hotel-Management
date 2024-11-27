package com.prog.Hotel_Management.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Hotel_Management.DTO.HotelDTO;
import com.prog.Hotel_Management.Entity.Hotel;
import com.prog.Hotel_Management.Repository.HotelRepository;

@Service
public class HotelService {
	@Autowired
	private HotelRepository hotelRepository;
	
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
		hotel.setAvailable(dto.isAvailable());
		return hotel;
	}
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
}
