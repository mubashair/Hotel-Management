package com.prog.Hotel_Management.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		//1-Convert DTO to entity
		Hotel hotel = convertToEntity(hotelDTO);
		//2-Save Entity to database
		Hotel savedHotel = hotelRepository.save(hotel);
		//3-Convert the saved entity back to DTO
		return converToDTO(savedHotel);
		
	}
	//update an existing hotel based on id(given)
	public HotelDTO updateHotel(HotelDTO hotelDTO, Long id) {
		//Reterive the existing hotel entity by id
		Hotel existingHotel = hotelRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Hote not found with ID:" +id));
		
		Optional.ofNullable(hotelDTO.getName()).ifPresent(existingHotel::setName);
		Optional.ofNullable(hotelDTO.getCity()).ifPresent(existingHotel::setCity);
		Optional.ofNullable(hotelDTO.getAddress()).ifPresent(existingHotel::setAddress);
		Optional.ofNullable(hotelDTO.getRating()).ifPresent(existingHotel::setRating);
		Optional.ofNullable(hotelDTO.isAvailable()).ifPresent(existingHotel::setAvailable);
		
		Hotel updatedHotel = hotelRepository.save(existingHotel);
		//Converts the updated Hotel entity into a HotelDTO.
		//Ensures that the response sent to the client is a DTO and does not expose the entity directly.
		//Why convert to DTO?

				//Follows best practices by separating the service layer’s entities from the API layer’s response model.
				//Ensures security and flexibility by controlling the fields exposed to the client.
		return converToDTO(updatedHotel);
	}
	
}
