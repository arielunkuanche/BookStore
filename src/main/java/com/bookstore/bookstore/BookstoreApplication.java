package com.bookstore.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bookstore.bookstore.domain.BookRepository;
import com.bookstore.bookstore.domain.Book;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner booksDemo(BookRepository repository){
		return (args) -> {
			log.info("save demo books");
			repository.save(new Book("A Farewell to Arms", "Emest Hemingway", "1232323-21", 1929, 34.99));
			repository.save(new Book("Animal Farm", "George Orwell", "2212343-5", 1945, 30.50));
			repository.save(new Book("To Kill a Mockingbird", "Harper Lee", "978-0061120084", 1960, 10.99));
			repository.save(new Book("The Great Gatsby", "F. Scott Fitzgerald", "978-0743273565", 1925, 12.50));
		
			log.info("fetch all books");
			for(Book book : repository.findAll()){
				log.info(book.toString());
			}

			log.info("fetch by individual author name");
			for(Book book : repository.findByAuthor("George Orwell")){
				log.info(book.toString());
			}

		};
	}

}
