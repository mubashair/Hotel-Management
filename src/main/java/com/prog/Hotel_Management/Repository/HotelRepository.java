package com.prog.Hotel_Management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prog.Hotel_Management.Entity.Hotel;
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
