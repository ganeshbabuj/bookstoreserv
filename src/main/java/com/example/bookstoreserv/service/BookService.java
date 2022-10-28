package com.example.bookstoreserv.service;

import com.example.bookstoreserv.entity.BookEntity;
import com.example.bookstoreserv.exception.NotFoundException;
import com.example.bookstoreserv.model.EnhancedBook;
import com.example.bookstoreserv.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    ModelMapper modelMapper;

    private BookRepository bookRepository;

    public BookService(BookRepository BookRepository) {
        this.bookRepository = BookRepository;
    }

    public List<EnhancedBook> findAllBooks() {
        List<EnhancedBook> bookList = new ArrayList<>();
        Iterable<BookEntity> bookEntityList = bookRepository.findAll();
        System.out.println("findAllBooks | bookEntityList: " + bookEntityList);

        bookEntityList.forEach(bookEntity -> {
            EnhancedBook book = modelMapper.map(bookEntity, EnhancedBook.class);
            bookList.add(book);
        });

        System.out.println("findAllBooks | bookList: " + bookList);
        return bookList;
    }

    public EnhancedBook getBook(Long bookId) {

        System.out.println("getBook | bookId: " + bookId);
        Optional<BookEntity> bookEntityOptional = bookRepository.findById(bookId);
        if(bookEntityOptional.isPresent()) {
            EnhancedBook book = new EnhancedBook();
            BeanUtils.copyProperties(bookEntityOptional.get(), book);
            System.out.println("getBook | returned book: " + book);
            return book;
        }
        throw new NotFoundException("Book Not Found", bookId);

    }

}
