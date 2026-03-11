package com.example.LibraryManagementApp.service;

import com.example.LibraryManagementApp.exception.BookNotFoundException;
import com.example.LibraryManagementApp.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookService {
    
    private List<Book> books = new ArrayList<>();
    private AtomicLong idCounter = new AtomicLong(1);
    
    public void addBook(Book book) {
        book.setId(idCounter.getAndIncrement());
        books.add(book);
    }
    
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }
    
    public Book getBookById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(id));
    }
    
    public void deleteBook(Long id) {
        Book book = getBookById(id);
        books.remove(book);
    }
}
