package com.bookstore.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bookstore.bookstore.domain.BookRepository;
import com.bookstore.bookstore.domain.Category;
import com.bookstore.bookstore.domain.CategoryRepository;
import com.bookstore.bookstore.domain.Book;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner booksDemo(BookRepository brepository, CategoryRepository crepository){
		return (args) -> {
			log.info("save categories");
			Category cat1 = new Category("Allegory");
			Category cat2 = new Category("Realism");
			Category cat3 = new Category("Southern Gothic Bildungsroman");
			Category cat4 = new Category("Tragedy");

			crepository.save(cat1);
			crepository.save(cat2);
			crepository.save(cat3);
			crepository.save(cat4);

			log.info("save demo books");
			brepository.save(new Book("A Farewell to Arms", "Emest Hemingway", "1232323-21", 1929, 34.99, cat1));
			brepository.save(new Book("Animal Farm", "George Orwell", "2212343-5", 1945, 30.50, cat2));
			brepository.save(new Book("To Kill a Mockingbird", "Harper Lee", "978-0061120084", 1960, 10.99, cat3));
			brepository.save(new Book("The Great Gatsby", "F. Scott Fitzgerald", "978-0743273565", 1925, 12.50,cat4));
		
			log.info("fetch all books");
			for(Book book : brepository.findAll()){
				log.info(book.toString());
			}

			log.info("fetch by individual author name");
			for(Book book : brepository.findByAuthor("George Orwell")){
				log.info(book.toString());
			}

		};
	}

}
