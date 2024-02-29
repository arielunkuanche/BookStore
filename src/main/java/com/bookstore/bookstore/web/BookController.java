package com.bookstore.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bookstore.bookstore.domain.BookRepository;
import com.bookstore.bookstore.domain.Category;
import com.bookstore.bookstore.domain.CategoryRepository;
import com.bookstore.bookstore.domain.Book;



@Controller
public class BookController {
    @Autowired
    public BookRepository brepository;

    @Autowired
    public CategoryRepository crepository;

    @RequestMapping(value="/index", method=RequestMethod.GET)
    public String bookList(Model model){
        model.addAttribute("books", brepository.findAll());
        return "booklist";
    }

    // restful service to get all books
    @RequestMapping(value="/books", method=RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest(){
        return(List<Book>) brepository.findAll();
    }

    // restful service to get book by id
    @RequestMapping(value="/book/{id}", method=RequestMethod.GET)
    public @ResponseBody Optional<Book> findStudentByIdRest(@PathVariable("id") Long bookId){
        return brepository.findById(bookId);
    }

    // restful service to adding new book
    @RequestMapping(value="/addbook", method=RequestMethod.POST)
    public @ResponseBody Book addNewBookwithRest(Book book){
        return brepository.save(book);
    }

    @RequestMapping(value="/add", method=RequestMethod.GET)
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("categories", crepository.findAll());
        return "addbook";
    }

    @RequestMapping(value="/save", method=RequestMethod.POST)
    public String save(Book book){
        brepository.save(book);
        return "redirect:/index";
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public String deleteBookById(@PathVariable("id") Long bookId, Model model){
        brepository.deleteById(bookId);
        return "redirect:/index";
    }

    @RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
    public String editBookById(@PathVariable("id") Long bookId, Model model){
        model.addAttribute("book", brepository.findById(bookId));
        model.addAttribute("categories", crepository.findAll());
        return "editbook";
    }

    }

