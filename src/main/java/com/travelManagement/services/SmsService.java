package com.travelManagement.services;

public interface SmsService {
    /**
     * Send a text message to given phone number (E.164 or plain).
     * @param phoneNumber phone number string (e.g. "+911234567890" or "91234567890")
     * @param message     message body
     * @return true if send simulated OK, false otherwise
     */
    boolean sendSms(String phoneNumber, String message);
}
