package com.tekarch.TafDatastoreService.Service.Interface;

import com.tekarch.TafDatastoreService.Model.Flights;

import java.util.List;
import java.util.Optional;

public interface FlightsService {
    Flights addFlight(Flights flight);
    Optional<Flights> getFlightById(Long id);
    List<Flights> getAllFlights();
    Flights updateFlight(Long id, Flights updatedFlight);
    void deleteFlight(Long id);

}
