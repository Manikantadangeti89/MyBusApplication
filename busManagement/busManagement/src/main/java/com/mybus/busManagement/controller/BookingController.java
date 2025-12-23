package com.mybus.busManagement.controller;

import com.mybus.busManagement.dto.BookingRequestDTO;
import com.mybus.busManagement.dto.BookingResponseDTO;
import com.mybus.busManagement.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {
    
    @Autowired
    private BookingService bookingService;
    
    // Create a new booking
    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(@Valid @RequestBody BookingRequestDTO bookingRequestDTO) {
        BookingResponseDTO bookingResponse = bookingService.createBooking(bookingRequestDTO);
        return new ResponseEntity<>(bookingResponse, HttpStatus.CREATED);
    }
    
    // Get all bookings
    @GetMapping
    public ResponseEntity<List<BookingResponseDTO>> getAllBookings() {
        List<BookingResponseDTO> bookings = bookingService.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
    
    // Get booking by ID
    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable Long id) {
        BookingResponseDTO booking = bookingService.getBookingById(id);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }
    
    // Get bookings by passenger email
    @GetMapping("/email/{email}")
    public ResponseEntity<List<BookingResponseDTO>> getBookingsByEmail(@PathVariable String email) {
        List<BookingResponseDTO> bookings = bookingService.getBookingsByEmail(email);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
    
    // Cancel a booking
    @PutMapping("/{id}/cancel")
    public ResponseEntity<BookingResponseDTO> cancelBooking(@PathVariable Long id) {
        BookingResponseDTO bookingResponse = bookingService.cancelBooking(id);
        return new ResponseEntity<>(bookingResponse, HttpStatus.OK);
    }
    
    // Get all confirmed bookings
    @GetMapping("/confirmed")
    public ResponseEntity<List<BookingResponseDTO>> getConfirmedBookings() {
        List<BookingResponseDTO> bookings = bookingService.getConfirmedBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
    
    // Get all cancelled bookings
    @GetMapping("/cancelled")
    public ResponseEntity<List<BookingResponseDTO>> getCancelledBookings() {
        List<BookingResponseDTO> bookings = bookingService.getCancelledBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
}

