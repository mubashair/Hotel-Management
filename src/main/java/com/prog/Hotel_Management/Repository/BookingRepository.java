package com.prog.Hotel_Management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prog.Hotel_Management.Entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
