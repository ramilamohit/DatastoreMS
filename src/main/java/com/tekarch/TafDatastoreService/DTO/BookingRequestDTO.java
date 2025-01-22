package com.tekarch.TafDatastoreService.DTO;

import lombok.Data;

@Data
public class BookingRequestDTO {
    private Long userId;
    private Long flightId;
    private String status; // e.g., Booked, Cancelled
}
