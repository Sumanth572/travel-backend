package com.travelManagement.services;

import org.springframework.stereotype.Service;

@Service
public class FakeSmsService implements SmsService {

    @Override
    public boolean sendSms(String phoneNumber, String message) {
        // Fake send - log to console for now
        System.out.println("==== FAKE SMS SEND ====");
        System.out.println("To   : " + phoneNumber);
        System.out.println("Body : " + message);
        System.out.println("=======================");
        // you could also write to a DB/log file here
        return true;
    }
}
