package com.example.tanmay.library_management.Service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.tanmay.library_management.Model.Book;
import com.example.tanmay.library_management.Model.BooksIssued;
import com.example.tanmay.library_management.Model.Student;
import com.example.tanmay.library_management.Repo.BookRepository;
import com.example.tanmay.library_management.Repo.BooksIssuedRepo;
import com.example.tanmay.library_management.Repo.StudentRepository;

@Service
public class IssueBooksService {
    
    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private BooksIssuedRepo booksIssuedRepo;

    public String issueBook(String bookId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Student student = studentRepo.findByUserUsername(authentication.getName()).get();
        BooksIssued booksIssued = new BooksIssued();
        Book book = bookRepo.findById(Integer.parseInt(bookId)).get();
        if(!book.getIsAvailable()) {
            return "Book is not available";
        }
        booksIssued.setBook(book);
        booksIssued.setStudent(student);
        booksIssued.setIssueDate(new Date(System.currentTimeMillis()));
        book.setIsAvailable(false);
        bookRepo.save(book);
        booksIssuedRepo.save(booksIssued);
        return "Book issued successfully";
    }

    public String returnBook(String bookId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Student student = studentRepo.findByUserUsername(authentication.getName()).get();
        Book book = bookRepo.findById(Integer.parseInt(bookId)).get();
        BooksIssued booksIssued = booksIssuedRepo.findByBookAndStudentAndIsReturnedFalse(book, student);
        if(booksIssued == null) {
            return "No record found for this book and student";
        }
        booksIssued.setReturnDate(new Date(System.currentTimeMillis()));
        booksIssued.setIsReturned(true);
        book.setIsAvailable(true);
        bookRepo.save(book);
        booksIssuedRepo.save(booksIssued);
        return "Book returned successfully";
    }

}
