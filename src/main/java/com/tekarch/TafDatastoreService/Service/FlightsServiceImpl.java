package com.tekarch.TafDatastoreService.Service;

import com.tekarch.TafDatastoreService.Model.Flights;
import com.tekarch.TafDatastoreService.Repositories.FlightsRepository;
import com.tekarch.TafDatastoreService.Service.Interface.FlightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightsServiceImpl implements FlightsService {
    @Autowired
    private FlightsRepository flightRepository;

    @Override
    public Flights addFlight(Flights flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Optional<Flights> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    @Override
    public List<Flights> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flights updateFlight(Long id, Flights updatedFlight) {
        return flightRepository.findById(id).map(flight -> {
            flight.setFlightNumber(updatedFlight.getFlightNumber());
            flight.setDeparture(updatedFlight.getDeparture());
            flight.setArrival(updatedFlight.getArrival());
            flight.setDepartureTime(updatedFlight.getDepartureTime());
            flight.setArrivalTime(updatedFlight.getArrivalTime());
            flight.setPrice(updatedFlight.getPrice());
            flight.setAvailableSeats(updatedFlight.getAvailableSeats());
            return flightRepository.save(flight);
        }).orElseThrow(() -> new RuntimeException("Flight not found with id: " + id));
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
}
