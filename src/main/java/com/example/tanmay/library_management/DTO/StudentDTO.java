package com.example.tanmay.library_management.DTO;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentDTO {

    public StudentDTO(int id, String name, int noOfBooksIssued, List<String> bookTitles) {
        this.id = id;
        this.name = name;
        this.noOfBooksIssued = noOfBooksIssued;
        this.bookTitles = bookTitles;
    }

    private int id;
    private String name;
    private int noOfBooksIssued;
    private List<String> bookTitles;
}
