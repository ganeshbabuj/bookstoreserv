package com.example.bookstoreserv.service;

import com.example.bookstoreserv.entity.AuthorEntity;
import com.example.bookstoreserv.entity.BookEntity;
import com.example.bookstoreserv.exception.NotFoundException;
import com.example.bookstoreserv.model.Author;
import com.example.bookstoreserv.model.EnhancedBook;
import com.example.bookstoreserv.repository.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    ModelMapper modelMapper;

    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> findAllAuthors() {

        List<Author> authorList = new ArrayList<>();
        Iterable<AuthorEntity> authorEntityList = authorRepository.findAll();
        BeanUtils.copyProperties(authorList, authorEntityList);
        authorEntityList.forEach(authorEntity -> {
            Author author = modelMapper.map(authorEntity, Author.class);
            authorList.add(author);
        });

        return authorList;
    }

    public Author getAuthor(Long authorId) {

        System.out.println("getAuthor | authorId: " + authorId);
        Optional<AuthorEntity> authorEntityOptional = authorRepository.findById(authorId);
        if(authorEntityOptional.isPresent()) {
            Author author = new Author();
            BeanUtils.copyProperties(authorEntityOptional.get(), author);
            System.out.println("getAuthor | returned author: " + author);
            return author;
        }
        throw new NotFoundException("Author Not Found", authorId);
    }

    public Author create(Author author) {
        AuthorEntity authorEntity = new AuthorEntity();
        modelMapper.map(author, authorEntity);
        AuthorEntity savedAuthorEntity = authorRepository.save(authorEntity);
        modelMapper.map(savedAuthorEntity, author);
        return author;
    }

    public Author update(Long id, Author author) {

        System.out.println("update Author: " + id + " | " + author);
        Optional<AuthorEntity> authorEntityOptional = authorRepository.findById(id);
        if(authorEntityOptional.isPresent()) {
            AuthorEntity authorEntity = authorEntityOptional.get();
            modelMapper.map(author, authorEntity);
            authorEntity.setId(id);
            AuthorEntity savedAuthorEntity = authorRepository.save(authorEntity);
            modelMapper.map(savedAuthorEntity, author);
            return author;
        }
        throw new NotFoundException("Author Not Found", id);
    }

}
