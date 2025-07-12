package com.bookstore.system;

import com.bookstore.model.Book;
import com.bookstore.service.MailService;
import com.bookstore.service.PurchaseService;
import com.bookstore.service.ShippingService;

import java.util.ArrayList;
import java.util.List;

public class QuantumBookStoreSystem {
    ArrayList<Book> inventory;
    ShippingService shippingService;;
    MailService mailService;

    public QuantumBookStoreSystem(ArrayList<Book> inventory, MailService mailService, ShippingService shippingService) {
        this.inventory = inventory;
        this.mailService = mailService;
        this.shippingService = shippingService;
    }
    public void addBook(Book book) {
        inventory.add(book);
    }
    public List<Book> removeOutdatedBooks(int numOfYears) {
        int currentYear = 2025; // As per provided date
        List<Book> removedBooks = new ArrayList<>();
        List<Book> booksToRemove = new ArrayList<>();

        for (Book book : inventory) {
            if (currentYear - book.getYear() >= numOfYears) {
                booksToRemove.add(book);
                removedBooks.add(book);
            }
        }

        for (Book book : booksToRemove) {
            inventory.remove(book);
            System.out.println("Quantum Bookstore: Removed outdated book " + book.getTitle());
        }

        return removedBooks;
    }
    public double buyBook(String isbn, int quantity, String email, String address) throws IllegalStateException, IllegalArgumentException {
        Book book = inventory.stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Book with ISBN " + isbn + " not found"));

        PurchaseService purchaseService = new PurchaseService(shippingService, mailService);
        book.Buy(purchaseService, quantity, email, address);
        if (book instanceof com.bookstore.model.PaperBook) {
            return purchaseService.purchase((com.bookstore.model.PaperBook) book, quantity, address, email);
        } else if (book instanceof com.bookstore.model.EBook) {
            return purchaseService.purchase((com.bookstore.model.EBook) book, quantity, email, address);
        } else {
            throw new IllegalStateException("Book " + book.getTitle() + " is not for sale");
        }
    }

}
