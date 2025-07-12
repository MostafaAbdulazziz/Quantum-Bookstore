package com.bookstore.model;

import com.bookstore.service.PurchaseService;

public class ShowcaseBook extends Book {
    public ShowcaseBook(String isbn, String title, String author, int year, double price) {
        super(isbn, title, author, year, price);
    }

    @Override
    public void Buy(PurchaseService purchaseService, int quantity,String email,String address) {
            purchaseService.purchase(this,quantity,email,address);
    }
}