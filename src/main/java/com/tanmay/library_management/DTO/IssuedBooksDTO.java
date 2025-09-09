package com.tanmay.library_management.DTO;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IssuedBooksDTO {
    private int id;
    private int studentId;
    private String studentName;
    private int bookId;
    private String bookTitle;
    private Date issueDate;
    private Boolean isReturned;
    private Date returnDate;
}
