package com.bookstore.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

