package com.tekarch.TafDatastoreService.Repositories;

import com.tekarch.TafDatastoreService.Model.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Bookings, Long> {
    List<Bookings> findByUserId(Long userId);
    List<Bookings> findByFlightId(Long flightId);

}
