package com.prog.Hotel_Management.Service;

import com.prog.Hotel_Management.DTO.BookingDTO;
import com.prog.Hotel_Management.Entity.Booking;
import com.prog.Hotel_Management.Entity.Hotel;
import com.prog.Hotel_Management.Repository.BookingRepository;

public class BookingService {
	
	private final BookingRepository bookingRepository;
	public BookingService(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}
	
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
	public Booking covertToEntity(BookingDTO bookingDTO, Hotel hotel) {
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
	
}
