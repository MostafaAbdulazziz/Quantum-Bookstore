package com.bookstore.service;


import com.bookstore.model.EBook;
import com.bookstore.model.PaperBook;
import com.bookstore.model.ShowcaseBook;
import com.bookstore.service.MailService;
import com.bookstore.service.ShippingService;

public class PurchaseService {
    private final ShippingService shippingService;
    private final MailService mailService;

    public PurchaseService(ShippingService shippingService, MailService mailService) {
        this.shippingService = shippingService;
        this.mailService = mailService;
    }

    public double purchase(PaperBook book, int quantity, String address, String email) throws IllegalStateException, IllegalArgumentException {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (book.getStock() < quantity) {
            throw new IllegalStateException("Insufficient stock for " + book.getTitle());
        }
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address is required for PaperBook");
        }
        book.setStock(book.getStock() - quantity);
        shippingService.ship(book.getTitle(), address);
        System.out.println("Purchased " + quantity + " copies of " + book.getTitle());
        double totalCost = book.getPrice() * quantity;
        System.out.println("Total cost for " + quantity + " copies of " + book.getTitle() + ": $" + totalCost);
        return totalCost;
    }

    public double purchase(EBook book, int quantity, String email, String address) throws IllegalArgumentException {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email is required for EBook");
        }
        mailService.sendEmail(book.getTitle(), email);
        System.out.println("Purchased " + quantity + " copies of " + book.getTitle() + " (" + book.getFiletype() + ")");
        double totalCost = book.getPrice() * quantity;
        System.out.println("Total cost for " + quantity + " copies of " + book.getTitle() + ": $" + totalCost);
        return totalCost;
    }

    public void purchase(ShowcaseBook showcaseBook, int quantity, String email, String address) {
        System.out.println("Cannot Purchase Showcase Book "+showcaseBook.getTitle());
    }
}
