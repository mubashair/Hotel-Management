package com.prog.Hotel_Management.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
		hotelDTO.setTotalRooms(hotel.getTotalRooms());
		hotelDTO.setAvailableRooms(hotel.getAvailableRooms());
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
		hotel.setTotalRooms(dto.getTotalRooms());
		hotel.setAvailableRooms(dto.getAvailableRooms());
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
	public void deleteHotel(Long id) {
		if(!hotelRepository.existsById(id)) {
			throw new HotelNotFoundException("Hotel Not Found with ID: "+id);
		}
		hotelRepository.deleteById(id);
	}
	//Here’s an example of populating the database using your service layer in Java:
	public void populateSampleHotels() {
		 // List of sample hotels with total and available rooms
	    List<HotelDTO> hotels = List.of(
	        new HotelDTO("Grand Plaza Hotel", "New York", "123 Broadway, NY 10001", 4.5f, true, 100, 50),
	        new HotelDTO("Oceanview Resort", "Miami", "456 Beachside Blvd, Miami, FL 33139", 4.7f, true, 200, 120),
	        new HotelDTO("Mountain Inn", "Denver", "789 Mountain Rd, Denver, CO 80202", 4.3f, false, 80, 0),
	        new HotelDTO("City Center Suites", "Chicago", "101 Downtown Ave, Chicago, IL 60601", 3.8f, true, 150, 75),
	        new HotelDTO("Heritage Hotel", "Boston", "202 History Ln, Boston, MA 02115", 4.0f, true, 90, 60),
	        new HotelDTO("Skyline Luxury", "Seattle", "303 Skyline Blvd, Seattle, WA 98101", 4.9f, true, 120, 100),
	        new HotelDTO("Lakeview Retreat", "Orlando", "404 Lakeside Dr, Orlando, FL 32801", 4.6f, true, 130, 110),
	        new HotelDTO("Urban Comfort", "Los Angeles", "505 Cityview St, Los Angeles, CA 90001", 4.2f, false, 75, 0),
	        new HotelDTO("Coastal Paradise", "San Diego", "606 Seaview Ln, San Diego, CA 92101", 4.8f, true, 140, 80),
	        new HotelDTO("Hillside Escape", "Austin", "707 Hilltop Blvd, Austin, TX 73301", 4.1f, true, 100, 90)
	    );

	    //hotels.forEach(hotelService::createHotel);
	    // Iterate over each sample hotel and save it to the database
	    for(HotelDTO hotelDTO : hotels) {
	    	Hotel hotel = convertToEntity(hotelDTO);//convert dto to entity
	    	hotelRepository.save(hotel);//save entity to repository
	    }
	}
    //Search feature
	//Call the repository method and convert the results into DTOs.
	public List<HotelDTO> searchHotels(String name, String city, Float rating, Boolean isAvailable){
		//Fetch the matching hotels from the repository
		List<Hotel> hotels = hotelRepository.searchHotels(name, city, rating, isAvailable);
		 // Convert List<Hotel> to List<HotelDTO> using a for-each loop
		List<HotelDTO> hotelDTOs = new ArrayList<>();
		for(Hotel hotel:hotels) {
			HotelDTO hotelDTO = converToDTO(hotel);
			hotelDTOs.add(hotelDTO);
			
		}
		return hotelDTOs;
	}
	
	//method to get the sorted hotel
	public List<HotelDTO> getSortedHotels(String sortBy, String sortDirection){
		// Validate sortBy field
	    List<String> validSortFields = List.of("name", "rating", "city");
	    if (!validSortFields.contains(sortBy)) {
	        throw new IllegalArgumentException("Invalid sort field: " + sortBy);
	    }

	    // Validate sortDirection
	    if (!sortDirection.equalsIgnoreCase("asc") && !sortDirection.equalsIgnoreCase("desc")) {
	        throw new IllegalArgumentException("Invalid sort direction: " + sortDirection);
	    }
		
		//Determine the sort direction
		Sort.Direction direction = Sort.Direction.fromString(sortDirection);
		//Create sort object
		Sort sort = Sort.by(direction, sortBy);
		// Fetch sorted hotels from the repository
		List<Hotel> hotels = hotelRepository.findAll(sort);
		//Creates an empty list (hotelDTOs) to store the sorted HotelDTO objects.
		//Iterates over the sorted Hotel entities.
		//For each Hotel, it:
		//Calls the converToDTO(Hotel hotel) method to convert the Hotel entity into a HotelDTO.
		//Adds the resulting HotelDTO to the hotelDTOs list.
		//convert the list of hotels to list of hotel dtos
		List<HotelDTO> hotelDTOs = new ArrayList<>();
		for(Hotel hotel:hotels) {
			hotelDTOs.add(converToDTO(hotel));
		}
		//Returns the sorted list of hotels in DTO format.
		//The client (controller or frontend) will receive only the relevant fields exposed in the HotelDTO.

		
		return hotelDTOs;
		
	}//end method getSortedHotels
			
}//end class HotelService
