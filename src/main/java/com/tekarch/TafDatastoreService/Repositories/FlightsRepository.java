package com.tekarch.TafDatastoreService.Repositories;

import com.tekarch.TafDatastoreService.Model.Bookings;
import com.tekarch.TafDatastoreService.Model.Flights;
import com.tekarch.TafDatastoreService.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightsRepository extends JpaRepository<Flights,Long> {
  //  List<Bookings> findByFlight(Flights flight);

    // Use when needed
 //   List<Bookings> findByUserAndFlight(Users user, Flights flight);
}
