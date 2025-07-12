package com.bookstore.model;

import com.bookstore.service.PurchaseService;

public class PaperBook extends Book {
    private int stock;


    public PaperBook(String isbn, String title, String author, int year, double price, int stock) {
        super(isbn, title, author, year, price);
        this.stock = stock;

    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public void Buy(PurchaseService purchaseService, int quantity, String email, String address) {
        purchaseService.purchase(this, quantity, email, address);
    }
}