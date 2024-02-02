package com.bookstore.bookstore.domain;

import jakarta.persistence.Entity;

@Entity
public class Book {

    private String title, author, isbn;
    private int year;
    private Long price;

    public Book(){};

    public Book(String title, String author, String isbn, int year, Long price){
        super();
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.year = year;
        this.price = price;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    
}
