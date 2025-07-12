package com.bookstore.model;

import com.bookstore.service.PurchaseService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Book {
    private final String isbn;
    private final String title;
    private final String author;
    private final int year;
    private final double price;
    public abstract void Buy(PurchaseService purchaseService,int quantity,String email,String address);


}
