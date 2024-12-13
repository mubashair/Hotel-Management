package com.prog.Hotel_Management.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prog.Hotel_Management.Entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	 // Find bookings by guest name (case-insensitive)
	List<Booking> findByGuestName(String guestName);
}
