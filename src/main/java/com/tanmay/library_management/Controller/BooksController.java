package com.tanmay.library_management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanmay.library_management.DTO.APIResponseWrapper;
import com.tanmay.library_management.DTO.BooksDTO;
import com.tanmay.library_management.Model.Book;
import com.tanmay.library_management.Service.BookService;

@RestController
@RequestMapping("api/books")
public class BooksController {

    @Autowired
    private BookService bookService;

    @GetMapping("getbooks")
    public ResponseEntity<APIResponseWrapper<List<BooksDTO>>> getAllBooks() {
        return ResponseEntity.ok(APIResponseWrapper.success("Books retrieved successfully", bookService.getAllBooks()));
    }

    @PostMapping("addbooks")
    public ResponseEntity<APIResponseWrapper<BooksDTO>> addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return ResponseEntity.ok(APIResponseWrapper.success("Book added successfully", null));
    }
}
