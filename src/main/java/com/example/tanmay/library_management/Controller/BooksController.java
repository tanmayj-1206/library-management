package com.example.tanmay.library_management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tanmay.library_management.Model.Book;
import com.example.tanmay.library_management.Service.BookService;

@RestController
@RequestMapping("books")
public class BooksController {

    @Autowired
    private BookService bookService;

    @GetMapping("getbooks")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("addbooks")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }
}
