package com.tekarch.TafDatastoreService.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingResponseDTO {
    private Long id;
    private Long userId;
    private Long flightId;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
