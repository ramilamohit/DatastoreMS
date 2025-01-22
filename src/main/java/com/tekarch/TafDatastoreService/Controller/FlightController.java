package com.tekarch.TafDatastoreService.Controller;

import com.tekarch.TafDatastoreService.Model.Flights;
import com.tekarch.TafDatastoreService.Model.Users;
import com.tekarch.TafDatastoreService.Service.FlightsServiceImpl;
import com.tekarch.TafDatastoreService.Service.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/flights")
public class FlightController {
    private final FlightsServiceImpl flightsServiceImpl;
    private static final Logger logger = LogManager.getLogger(FlightController.class);
    public FlightController(FlightsServiceImpl flightsServiceImpl) {
        this.flightsServiceImpl = flightsServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Flights> addFlight(@RequestBody Flights flight) {
        return ResponseEntity.ok(flightsServiceImpl.addFlight(flight));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flights> getFlightById(@PathVariable Long id) {
   return flightsServiceImpl.getFlightById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Flights>> getAllFlights() {
        return ResponseEntity.ok(flightsServiceImpl.getAllFlights());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flights> updateFlight(@PathVariable Long id, @RequestBody Flights updatedFlight) {
        try {
            return ResponseEntity.ok(flightsServiceImpl.updateFlight(id, updatedFlight));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightsServiceImpl.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }
}
