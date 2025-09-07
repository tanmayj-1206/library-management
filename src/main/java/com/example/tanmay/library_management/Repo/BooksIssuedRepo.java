package com.example.tanmay.library_management.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tanmay.library_management.Model.Book;
import com.example.tanmay.library_management.Model.BooksIssued;
import com.example.tanmay.library_management.Model.Student;

@Repository
public interface BooksIssuedRepo extends JpaRepository<BooksIssued, Integer> {

    BooksIssued findByBookAndStudentAndIsReturnedFalse(Book book, Student student);

}
