package com.example.bookstoreserv.repository;

import com.example.bookstoreserv.entity.AuthorEntity;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {
}
