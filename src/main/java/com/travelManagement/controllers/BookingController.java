package com.travelManagement.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelManagement.model.Booking;
import com.travelManagement.services.BookingService;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:4200")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/book")
    public ResponseEntity<?> makeBooking(@RequestBody Booking booking) {
        try {
            Booking savedBooking = bookingService.makeBooking(
                    booking.getUser().getId(),
                    booking.getTravelPackage().getId(),
                    booking
            );

            // Return booking info to frontend
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Booking successful");
            response.put("bookingId", savedBooking.getId());
            response.put("totalCost", savedBooking.getTotalCost());

            return ResponseEntity.ok(response);

        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Internal server error"));
        }
    }
}
