package com.example.bookstoreserv.controller;

import com.example.bookstoreserv.entity.AuthorEntity;
import com.example.bookstoreserv.entity.BookEntity;
import com.example.bookstoreserv.model.Author;
import com.example.bookstoreserv.model.Book;
import com.example.bookstoreserv.model.EnhancedBook;
import com.example.bookstoreserv.service.AuthorService;
import com.example.bookstoreserv.service.BookService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class StoreController {

    private BookService bookService;

    private AuthorService authorService;

    public StoreController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @QueryMapping
    public List<Author> authors() {
        return authorService.findAllAuthors();
    }

    @MutationMapping
    public Author createAuthor(@Argument String firstName, @Argument String lastName, @Argument String country) {
        return authorService.create(new Author(firstName, lastName, country));
    }

    @MutationMapping
    public Author updateAuthor(@Argument Long id, @Argument String firstName, @Argument String lastName, @Argument String country) {
        return authorService.update(id, new Author(firstName, lastName, country));
    }

    @QueryMapping
    public List<EnhancedBook> books() {
        return bookService.findAllBooks();
    }

    @QueryMapping
    public EnhancedBook book(@Argument Long id) {
        return bookService.getBook(id);
    }

    @SchemaMapping(typeName="Book", field="author")
    public Author getAuthor(EnhancedBook book) {
        return authorService.getAuthor(book.getAuthor().getId());
    }

}
