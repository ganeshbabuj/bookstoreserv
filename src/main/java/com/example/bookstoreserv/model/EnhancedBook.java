package com.example.bookstoreserv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnhancedBook {

    private Long id;
    private String title;
    private String isbn;
    private int numberOfPages;
    private Author author;

    public EnhancedBook(String title, String isbn, int numberOfPages, Author author) {
        this.title = title;
        this.isbn = isbn;
        this.numberOfPages = numberOfPages;
        this.author = author;
    }

}
