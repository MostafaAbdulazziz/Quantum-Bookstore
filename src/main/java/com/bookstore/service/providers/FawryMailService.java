package com.bookstore.service.providers;

import com.bookstore.service.MailService;

public class FawryMailService implements MailService {
    @Override
    public void sendEmail(String bookName, String emailAddress) {
        System.out.println("Quantum Bookstore: Sending Ebook: " + bookName + " to " + emailAddress + "...");
    }
}
