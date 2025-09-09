package com.tanmay.library_management.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tanmay.library_management.DTO.BooksDTO;
import com.tanmay.library_management.Model.Book;
import com.tanmay.library_management.Repo.BookRepository;
import com.tanmay.library_management.Utility.MapperDTOUtil;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<BooksDTO> getAllBooks() {
        return bookRepository.findAll().stream()
            .map(MapperDTOUtil::toBooksDTO)
            .toList();
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }
}
