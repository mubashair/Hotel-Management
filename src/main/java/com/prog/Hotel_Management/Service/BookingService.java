package com.prog.Hotel_Management.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Hotel_Management.DTO.BookingDTO;
import com.prog.Hotel_Management.Entity.Booking;
import com.prog.Hotel_Management.Entity.Hotel;
import com.prog.Hotel_Management.Repository.BookingRepository;
import com.prog.Hotel_Management.Repository.HotelRepository;
import com.prog.Hotel_Management.exceptions.HotelNotFoundException;
import com.prog.Hotel_Management.exceptions.InsufficientRoomsException;
import com.prog.Hotel_Management.exceptions.InvalidBookingException;

import jakarta.transaction.Transactional;
@Service
public class BookingService {
	
	private final BookingRepository bookingRepository;
	public BookingService(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}
	@Autowired
	private HotelRepository hotelRepository;
	
	//Convert Booking entity to booking DTO
	public BookingDTO convertToDTOs(Booking booking) {
		BookingDTO bookingDTO = new BookingDTO();
		bookingDTO.setId(booking.getId());
		bookingDTO.setHotelId(booking.getHotel().getId());// // Setting hotel ID from the Hotel entity
		bookingDTO.setGuestName(booking.getGuestName());
		bookingDTO.setCheckInDate(booking.getCheckInDate());
		bookingDTO.setCheckOutDate(booking.getCheckOutDate());
		bookingDTO.setRoomsBooked(booking.getRoomsBooked());
		return bookingDTO;
	}
	//Convert BookingDTO to Booking entity
	public Booking convertToEntity(BookingDTO bookingDTO, Hotel hotel) {
		// Create a new Booking entity instance
	    Booking booking = new Booking();

	    // Set the associated hotel for the booking using the provided Hotel entity
	    // This establishes the relationship between the booking and its hotel
	    //Relationship comment: Highlights the significance of setting the hotel field 
	    //to establish the relationship between the Booking and Hotel entities.
	    booking.setHotel(hotel);

	    // Set the guest name in the Booking entity using the value from the BookingDTO
	    // This is the name of the person making the booking
	    booking.setGuestName(bookingDTO.getGuestName());

	    // Set the check-in date for the booking
	    // This defines when the guest will start their stay
	    booking.setCheckInDate(bookingDTO.getCheckInDate());

	    // Set the check-out date for the booking
	    // This defines when the guest will leave
	    booking.setCheckOutDate(bookingDTO.getCheckOutDate());

	    // Set the number of rooms booked for the stay
	    // This value indicates how many rooms the guest has reserved
	    booking.setRoomsBooked(bookingDTO.getRoomsBooked());

	    // Return the fully populated Booking entity
	    // The caller of this method will use this entity to persist data into the database
	    return booking;
	}
	//Creaate a Booking
	@Transactional
	public BookingDTO createBooking(BookingDTO bookingDTO) {
		//Validate input
		if(bookingDTO.getRoomsBooked() <= 0) {
			throw new InvalidBookingException("Number of rooms booked must be greate than zero");
		}
		// Retrieve the Hotel entity based on the hotelId provided in the BookingDTO
	    // If the hotel doesn't exist, throw a custom exception
		Hotel hotel = hotelRepository.findById(bookingDTO.getHotelId())
				.orElseThrow(() -> new HotelNotFoundException("Hotel with id:" + bookingDTO.getHotelId() + " not found"));
		
		// Check if the hotel has enough available rooms for the requested booking
		if(hotel.getAvailableRooms() < bookingDTO.getRoomsBooked() ) {
			throw new InsufficientRoomsException("Not enough rooms available. Available:" 
		                                         +hotel.getAvailableRooms()+ ", Requested: " +bookingDTO.getRoomsBooked());
		}
		// Update the available rooms in the hotel entity
		hotel.setAvailableRooms(hotel.getAvailableRooms() - bookingDTO.getRoomsBooked());
		hotelRepository.save(hotel);// Persist the updated hotel entity
		// Convert the BookingDTO into a Booking entity, associating it with the retrieved Hotel entity
		Booking booking = convertToEntity(bookingDTO, hotel);
		// Save the new booking entity to the database
		Booking savedBooking = bookingRepository.save(booking);
		// Convert the saved Booking entity back into a BookingDTO and return it as the response
		BookingDTO  responseBookingDTO = convertToDTOs(savedBooking);
		return responseBookingDTO;
	}
}
