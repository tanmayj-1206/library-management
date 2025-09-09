package com.example.tanmay.library_management.DTO;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentDTO {

    private int id;
    private String name;
    private int noOfBooksIssued;
    private List<IssuedBooks> booksIssued;

    @Data
    @NoArgsConstructor
    public static class IssuedBooks {
        private int bookId;
        private String bookTitle;
    }
}
