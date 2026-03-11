package com.example.LibraryManagementApp.controller;

import com.example.LibraryManagementApp.model.Book;
import com.example.LibraryManagementApp.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }
    
    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") Book book, 
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-book";
        }
        
        bookService.addBook(book);
        return "redirect:/books/view";
    }
    
    @GetMapping("/view")
    public String viewAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "view-books";
    }
    
    @GetMapping("/details/{id}")
    public String viewBookDetails(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "book-details";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books/view";
    }
}
