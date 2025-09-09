package com.tanmay.library_management.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tanmay.library_management.Model.Book;
import com.tanmay.library_management.Model.BooksIssued;
import com.tanmay.library_management.Model.Student;

@Repository
public interface BooksIssuedRepo extends JpaRepository<BooksIssued, Integer> {

    BooksIssued findFirstByBookAndStudentAndIsReturnedFalse(Book book, Student student);

}
