package com.bookstore;

import com.bookstore.model.EBook;
import com.bookstore.model.PaperBook;
import com.bookstore.model.ShowcaseBook;
import com.bookstore.service.PurchaseService;
import com.bookstore.service.providers.FawryMailService;
import com.bookstore.service.providers.FawryShippingService;
import com.bookstore.system.QuantumBookStoreSystem;

import java.util.ArrayList;

public class QuantumBookStoreFullTest {
    public static void main(String[] args) {
        // Initialize services
        FawryShippingService shippingService = new FawryShippingService();
        FawryMailService mailService = new FawryMailService();
        PurchaseService purchaseService = new PurchaseService(shippingService, mailService);

        // Initialize bookstore
        QuantumBookStoreSystem bookstore = new QuantumBookStoreSystem(new ArrayList<>(), mailService, shippingService);

        // Add books
        PaperBook paperBook = new PaperBook("1234567890", "Java Programming", "John Doe", 2020, 29.99, 10);
        EBook eBook = new EBook("0987654321", "Python Guide", "Jane Smith", 2022, 19.99, "PDF");
        ShowcaseBook showcaseBook = new ShowcaseBook("1122334455", "Rare Manuscript", "Alice Brown", 2015, 999.99);
        bookstore.addBook(paperBook);
        bookstore.addBook(eBook);
        bookstore.addBook(showcaseBook);

        // Test buying books
        try {
            System.out.println("\n--- Buying PaperBook ---");
            double cost = bookstore.buyBook("1234567890", 2, null, "123 Main St");
            System.out.println("Quantum Bookstore: Paid $" + cost);

            System.out.println("\n--- Buying EBook ---");
            cost = bookstore.buyBook("0987654321", 1, "user@example.com", null);
            System.out.println("Quantum Bookstore: Paid $" + cost);

            System.out.println("\n--- Buying ShowcaseBook (should fail) ---");
            cost = bookstore.buyBook("1122334455", 1, null, null);
            System.out.println("Quantum Bookstore: Paid $" + cost);
        } catch (Exception e) {
            System.out.println("Quantum Bookstore: " + e.getMessage());
        }

        // Test buying with insufficient stock
        try {
            System.out.println("\n--- Buying PaperBook with insufficient stock ---");
            bookstore.buyBook("1234567890", 100, null, "123 Main St");
        } catch (Exception e) {
            System.out.println("Quantum Bookstore: " + e.getMessage());
        }

        // Test removing outdated books
        System.out.println("\n--- Removing books older than 5 years ---");
        bookstore.removeOutdatedBooks(5);
    }
}