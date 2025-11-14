package com.travelManagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travelManagement.model.Booking;
import com.travelManagement.model.TravelPackage;
import com.travelManagement.model.User;
import com.travelManagement.repository.BookingRepository;
import com.travelManagement.repository.TravelPackageRepository;
import com.travelManagement.repository.UserRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TravelPackageRepository travelPackageRepository;

    @Autowired
    private SmsService smsService;

    @Transactional
    public Booking makeBooking(Long userId, Long packageId, Booking bookingDetails) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        TravelPackage travelPackage = travelPackageRepository.findById(packageId)
                .orElseThrow(() -> new RuntimeException("Package not found"));

        bookingDetails.setUser(user);
        bookingDetails.setTravelPackage(travelPackage);

        Booking savedBooking = bookingRepository.save(bookingDetails);

        String smsMessage = String.format(
                "Hi %s, your booking for %s is received. Date: %s | People: %d | Total: ₹%.2f | Booking ID: %d",
                user.getName(),
                travelPackage.getPackageName() != null ? travelPackage.getPackageName() : "Package-" + travelPackage.getPackageId(),
                savedBooking.getBookingDate(),
                savedBooking.getNumberOfPeople(),
                savedBooking.getTotalCost(),
                savedBooking.getId()
        );

        String phone = user.getPhone();
        if (phone != null) {
            phone = phone.trim();
            if (!phone.startsWith("+") && phone.length() == 10) {
                phone = "+91" + phone;
            }
            try {
                boolean ok = smsService.sendSms(phone, smsMessage);
                if (!ok) {
                    System.out.println("SMS sending failed for booking ID " + savedBooking.getId());
                }
            } catch (Exception ex) {
                System.out.println("Failed to send SMS for booking " + savedBooking.getId() + ": " + ex.getMessage());
            }
        } else {
            System.out.println("User phone not available for userId " + userId + ". SMS not sent.");
        }

        return savedBooking;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
