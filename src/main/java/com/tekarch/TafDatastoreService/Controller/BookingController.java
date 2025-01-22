package com.tekarch.TafDatastoreService.Controller;

import com.tekarch.TafDatastoreService.DTO.BookingRequestDTO;
import com.tekarch.TafDatastoreService.DTO.BookingResponseDTO;
import com.tekarch.TafDatastoreService.Model.Bookings;
import com.tekarch.TafDatastoreService.Service.BookingServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingServiceImpl bookingServiceImpl;
    private static final Logger logger = LogManager.getLogger(BookingController.class);
    public BookingController(BookingServiceImpl bookingServiceImpl) {
        this.bookingServiceImpl = bookingServiceImpl;
    }

    @PostMapping
    public ResponseEntity<BookingResponseDTO> bookFlight(@RequestBody BookingRequestDTO bookingRequestDTO) {
        return ResponseEntity.ok(bookingServiceImpl.createBooking(bookingRequestDTO));
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingServiceImpl.getBookingById(bookingId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponseDTO>> getBookingsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingServiceImpl.getBookingsByUserId(userId));
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDTO> deleteBooking(@PathVariable Long bookingId) {

        return ResponseEntity.ok(bookingServiceImpl.deleteBooking(bookingId));
    }
}
