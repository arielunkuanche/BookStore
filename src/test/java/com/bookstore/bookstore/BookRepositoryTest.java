package com.bookstore.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.bookstore.bookstore.domain.Book;
import com.bookstore.bookstore.domain.BookRepository;
import com.bookstore.bookstore.domain.Category;
import com.bookstore.bookstore.domain.CategoryRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

//@DataJdbcTest

//ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BookstoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest{

    @Autowired
    private BookRepository brepository;

    @Autowired
    private CategoryRepository crepository;

    @Test
    public void findByAuthorShouldReturnBook(){
        List<Book> books = brepository.findByAuthor("Harper Lee");

        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Harper Lee");
    }

    @Test
    public void createNewBookShouldReturnTrue(){
        Category category = new Category("Fantasy");
        crepository.save(category);
        Book book = new Book("The Lord of the Rings", "J.R.R. Tolkien", "978-0618640157", 1954, 19.99, category);
        brepository.save(book);
        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void deleteNewBookShouldReturnTrue(){
        List<Book> books = brepository.findByAuthor("George Orwell");
        Book book = books.get(0);
        brepository.delete(book);

        List<Book> newBooks = brepository.findByAuthor("George Orwell");
        assertThat(newBooks).hasSize(0);
        
    }
}