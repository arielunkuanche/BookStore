package com.bookstore.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bookstore.bookstore.domain.BookRepository;

import com.bookstore.bookstore.domain.Book;


@Controller
public class BookController {
    @Autowired
    public BookRepository repository;

    @RequestMapping(value="/index", method=RequestMethod.GET)
    public String bookList(Model model){
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

    @RequestMapping(value="/add", method=RequestMethod.GET)
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @RequestMapping(value="/save", method=RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:/index";
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public String deleteBookById(@PathVariable("id") Long bookId, Model model){
        repository.deleteById(bookId);
        return "redirect:/index";
    }


    }

