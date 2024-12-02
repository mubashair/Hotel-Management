package com.prog.Hotel_Management.Service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Hotel_Management.DTO.HotelDTO;
import com.prog.Hotel_Management.Entity.Hotel;
import com.prog.Hotel_Management.Repository.HotelRepository;
import com.prog.Hotel_Management.exceptions.HotelNotFoundException;

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
	//update an existing hotel based on id(given)
	public HotelDTO updateHotel(HotelDTO hotelDTO, Long id) {
		//Reterive the existing hotel entity by id
		// Step 1: Retrieve the existing hotel entity by ID, handle Optional directly
		Hotel existingHotel = hotelRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Hote not found with ID:" +id));
		
		// Step 2: Update the fields dynamically with a helper method named updateEntityFields()
		updateEntityFields(existingHotel, hotelDTO);
		
		
		//3-Save the updated entity to the database
		Hotel updatedHotel = hotelRepository.save(existingHotel);
		//4-Converts the updated Hotel entity into a HotelDTO. and return
		//Ensures that the response sent to the client is a DTO and does not expose the entity directly.
		//Why convert to DTO?

				//Follows best practices by separating the service layer’s entities from the API layer’s response model.
				//Ensures security and flexibility by controlling the fields exposed to the client.
		return converToDTO(updatedHotel);
	}
	//Helper method to Update the fields of existing hotel 
	private void updateEntityFields(Hotel existingHotel, HotelDTO hotelDTO) {
		Optional.ofNullable(hotelDTO.getName()).ifPresent(existingHotel::setName);
		Optional.ofNullable(hotelDTO.getCity()).ifPresent(existingHotel::setCity);
		Optional.ofNullable(hotelDTO.getAddress()).ifPresent(existingHotel::setAddress);
		Optional.ofNullable(hotelDTO.getRating()).ifPresent(existingHotel::setRating);
		Optional.ofNullable(hotelDTO.isAvailable()).ifPresent(existingHotel::setAvailable);
		
	}
	//Get hotel by id
	public HotelDTO getHotelById(Long id){
		//Fetch the hotel entity by id
		Hotel hotelBox = hotelRepository.findById(id)
				.orElseThrow(()-> new HotelNotFoundException("Hotel Not Found with ID:"+ id));
		//convert entity to DTO
		return converToDTO(hotelBox);
	}
	//Delete hotel by id
	public void deleteHotelBy(Long id) {
		if(!hotelRepository.existsById(id)) {
			throw new HotelNotFoundException("Hotel Not Found with ID: "+id);
		}
		hotelRepository.deleteById(id);
	}
			
}
