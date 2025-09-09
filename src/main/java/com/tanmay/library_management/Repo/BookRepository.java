package com.tanmay.library_management.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tanmay.library_management.Model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    
} 