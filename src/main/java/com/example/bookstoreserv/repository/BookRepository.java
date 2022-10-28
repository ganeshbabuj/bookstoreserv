package com.example.bookstoreserv.repository;

import com.example.bookstoreserv.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookEntity, Long> {
}
