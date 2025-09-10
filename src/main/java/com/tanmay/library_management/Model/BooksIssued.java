package com.tanmay.library_management.Model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "books_issued")
@NoArgsConstructor
public class BooksIssued {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference(value = "student-issued")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonBackReference(value = "book-issued")
    private Book book;

    private Date issueDate;
    private Date dueDate;
    private int fine;
    private Date returnDate;
    private Boolean isReturned = false;
}
