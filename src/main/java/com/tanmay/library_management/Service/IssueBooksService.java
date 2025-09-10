package com.tanmay.library_management.Service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tanmay.library_management.DTO.IssuedBooksDTO;
import com.tanmay.library_management.Model.Book;
import com.tanmay.library_management.Model.BooksIssued;
import com.tanmay.library_management.Model.Student;
import com.tanmay.library_management.Repo.BookRepository;
import com.tanmay.library_management.Repo.BooksIssuedRepo;
import com.tanmay.library_management.Repo.StudentRepository;
import com.tanmay.library_management.Utility.MapperDTOUtil;

@Service
public class IssueBooksService {
    int BORROW_TIME = 1000 * 60 * 60 * 24 * 14;
    int FINE_PER_DAY = 10; 
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
        if(book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Book is not available");
        }
        booksIssued.setBook(book);
        booksIssued.setStudent(student);
        booksIssued.setIssueDate(new Date(System.currentTimeMillis()));
        booksIssued.setDueDate(new Date(System.currentTimeMillis() + BORROW_TIME));
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepo.save(book);
        booksIssuedRepo.save(booksIssued);
        return "Book issued successfully";
    }

    public String returnBook(String bookId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Student student = studentRepo.findByUserUsername(authentication.getName()).get();
        Book book = bookRepo.findById(Integer.parseInt(bookId)).get();
        BooksIssued booksIssued = booksIssuedRepo.findFirstByBookAndStudentAndIsReturnedFalse(book, student);
        if(booksIssued == null) {
            throw new RuntimeException("No record found for this book and student");
        }
        booksIssued.setReturnDate(new Date(System.currentTimeMillis()));
        booksIssued.setIsReturned(true);
        booksIssued.setFine((int) ((booksIssued.getReturnDate().getTime() - booksIssued.getDueDate().getTime()) / (1000 * 60 * 60 * 24)) * FINE_PER_DAY);
        if(booksIssued.getFine() < 0) {
            booksIssued.setFine(0);
        }
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepo.save(book);
        booksIssuedRepo.save(booksIssued);
        return "Book returned successfully";
    }

    public List<IssuedBooksDTO> getIssuedBooks() {
        return booksIssuedRepo.findAll().stream()
            .map(MapperDTOUtil::toIssuedBooksDTO)
            .toList();
    }

}
