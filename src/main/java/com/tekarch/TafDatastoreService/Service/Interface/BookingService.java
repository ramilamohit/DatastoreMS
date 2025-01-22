package com.tekarch.TafDatastoreService.Service.Interface;

import com.tekarch.TafDatastoreService.Controller.FlightController;
import com.tekarch.TafDatastoreService.DTO.BookingRequestDTO;
import com.tekarch.TafDatastoreService.DTO.BookingResponseDTO;
import com.tekarch.TafDatastoreService.Model.Bookings;
import com.tekarch.TafDatastoreService.Service.BookingServiceImpl;
import com.tekarch.TafDatastoreService.Service.FlightsServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public interface BookingService {
    BookingResponseDTO createBooking(BookingRequestDTO bookingRequestDTO);
    BookingResponseDTO getBookingById(Long bookingId);
    List<BookingResponseDTO> getBookingsByUserId(Long userId);
    BookingResponseDTO deleteBooking(Long bookingId);
}
