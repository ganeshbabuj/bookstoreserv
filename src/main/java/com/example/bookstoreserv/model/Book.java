package com.example.bookstoreserv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private Long id;
    private String title;
    private String isbn;
    private int numberOfPages;
    private Long authorId;

    public Book(String title, String isbn, int numberOfPages, Long authorId) {
        this.title = title;
        this.isbn = isbn;
        this.numberOfPages = numberOfPages;
        this.authorId = authorId;
    }
}
