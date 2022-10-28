package com.example.bookstoreserv;

import com.example.bookstoreserv.entity.AuthorEntity;
import com.example.bookstoreserv.entity.BookEntity;
import com.example.bookstoreserv.repository.AuthorRepository;
import com.example.bookstoreserv.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookstoreservApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreservApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {

		return new ModelMapper();
	}

	@Bean
	public CommandLineRunner initialize(BookRepository bookRepository, AuthorRepository authorRepository) {
		return (args) -> {

			AuthorEntity authorEntity1 = new AuthorEntity("Firstname1", "Lastname1", "India");
			authorRepository.save(authorEntity1);
			bookRepository.save(new BookEntity("Bookname1", "1234567890", 500, authorEntity1.getId()));

			AuthorEntity authorEntity2 = new AuthorEntity("Firstname2", "Lastname2", "US");
			authorRepository.save(authorEntity2);
			bookRepository.save(new BookEntity("Bookname2", "4567890123", 300, authorEntity2.getId()));

		};
	}

}
