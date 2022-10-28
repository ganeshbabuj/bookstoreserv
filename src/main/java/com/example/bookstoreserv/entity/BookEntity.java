package com.example.bookstoreserv.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String isbn;

    private int numberOfPages;

    private Long authorId;

    public BookEntity(String title, String isbn, int numberOfPages, Long authorId) {
        this.title = title;
        this.isbn = isbn;
        this.numberOfPages = numberOfPages;
        this.authorId = authorId;
    }
}
