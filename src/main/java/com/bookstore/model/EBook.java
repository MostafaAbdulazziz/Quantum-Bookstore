package com.bookstore.model;

import com.bookstore.service.PurchaseService;

public class EBook extends Book {
    private final String filetype;


    public EBook(String isbn, String title, String author, int year, double price, String filetype) {
        super(isbn, title, author, year, price);
        this.filetype = filetype;
    }

    public String getFiletype() {
        return filetype;
    }

    @Override
    public void Buy(PurchaseService purchaseService, int quantity, String email, String address) {
            purchaseService.purchase(this, quantity, email, address);
    }
}