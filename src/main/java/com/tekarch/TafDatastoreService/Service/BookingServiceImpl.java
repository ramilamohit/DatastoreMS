package com.tekarch.TafDatastoreService.Service;

import com.tekarch.TafDatastoreService.DTO.BookingRequestDTO;
import com.tekarch.TafDatastoreService.DTO.BookingResponseDTO;
import com.tekarch.TafDatastoreService.Exception.ResourceNotFoundException;
import com.tekarch.TafDatastoreService.Model.Bookings;
import com.tekarch.TafDatastoreService.Model.Flights;
import com.tekarch.TafDatastoreService.Model.Users;
import com.tekarch.TafDatastoreService.Repositories.BookingRepository;
import com.tekarch.TafDatastoreService.Repositories.FlightsRepository;
import com.tekarch.TafDatastoreService.Repositories.UserRepository;
import com.tekarch.TafDatastoreService.Service.Interface.BookingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightsRepository flightsRepository;

    @Override
    public BookingResponseDTO createBooking(BookingRequestDTO bookingRequestDTO) {
        Users user = userRepository.findById(bookingRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + bookingRequestDTO.getUserId()));
        Flights flight = flightsRepository.findById(bookingRequestDTO.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found with ID: " + bookingRequestDTO.getFlightId()));

        if (flight.getAvailableSeats() <= 0) {
            throw new RuntimeException("No available seats for flight number: " + flight.getFlightNumber());
        }

        Bookings booking = new Bookings();
        booking.setUser(user); // Set the user entity
        booking.setFlight(flight);// Set the flight entity
        booking.setStatus(bookingRequestDTO.getStatus());
        booking.setCreatedAt(LocalDateTime.now());
        booking.setUpdatedAt(LocalDateTime.now());

        bookingRepository.save(booking);

        flight.setAvailableSeats(flight.getAvailableSeats() - 1);
        flightsRepository.save(flight);

        return mapToDTO(booking);

    }
    private BookingResponseDTO mapToDTO(Bookings booking) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setId(booking.getId());
        dto.setUserId(booking.getUser().getId());
        dto.setFlightId(booking.getFlight().getId());
        dto.setStatus(booking.getStatus());
        dto.setCreatedAt(booking.getCreatedAt());
        dto.setUpdatedAt(booking.getUpdatedAt());
        return dto;
        }
    @Override
    public BookingResponseDTO getBookingById(Long bookingId) {
        Bookings booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        return mapToDTO(booking);
    }

    @Override
    public List<BookingResponseDTO> getBookingsByUserId(Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        List<Bookings> bookings = bookingRepository.findByUserId(user.getId());

        List<BookingResponseDTO> responses = new ArrayList<>();
        for (Bookings booking : bookings) {
            responses.add(mapToDTO(booking));
        }
        return responses;
    }
    @Override
    public BookingResponseDTO deleteBooking(Long bookingId) {
        Bookings booking = bookingRepository.findById(bookingId).orElseThrow(()->new EntityNotFoundException("Record not found for booking Id "+bookingId));
        booking.setStatus("cancelled");
        bookingRepository.save(booking);
        return mapToDTO(booking);
    }
}
