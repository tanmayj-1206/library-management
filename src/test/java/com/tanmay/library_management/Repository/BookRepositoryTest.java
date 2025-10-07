package com.tanmay.library_management.Repository;

import com.tanmay.library_management.Repo.BookRepository;
import com.tanmay.library_management.Model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookRepositoryTest {

    @Mock
    private BookRepository bookRepository;

    @Test
    public void testFindById() {
        // Create a book instance
        Book book = new Book();
        book.setId(1);
        book.setTitle("The Great Gatsby");
        book.setAuthor("F. Scott Fitzgerald");

        // Mock the behavior of the repository
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        // Call the method and assert the result
        Optional<Book> foundBook = bookRepository.findById(1);
        assertEquals("The Great Gatsby", foundBook.get().getTitle());
    }
}