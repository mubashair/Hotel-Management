package com.prog.Hotel_Management.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prog.Hotel_Management.Entity.Hotel;
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
	// Custom query for dynamic search
    @Query("SELECT h FROM Hotel h WHERE " +
           "(:name IS NULL OR LOWER(h.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
           "(:city IS NULL OR LOWER(h.city) LIKE LOWER(CONCAT('%', :city, '%'))) AND " +
           "(:rating IS NULL OR h.rating >= :rating) AND " +
           "(:isAvailable IS NULL OR h.isAvailable = :isAvailable)")
    List<Hotel> searchHotels(String name, String city, Float rating, Boolean isAvailable);

}
