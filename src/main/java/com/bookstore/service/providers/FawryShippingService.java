package com.bookstore.service.providers;

import com.bookstore.service.ShippingService;

public class FawryShippingService implements ShippingService {
    @Override
    public void ship(String bookName, String address) {
        System.out.println("Quantum Bookstore: Shipping Book " + bookName + " to " + address + "...");
    }
}