package com.tanmay.library_management.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BooksDTO {
    private int id;
    private String title;
    private String author;
    private Boolean isAvailable;
    private IssuedTo issuedTo;

    @Data
    @NoArgsConstructor
    public static class IssuedTo {
        private int studentId;
        private String studentName;
    }
}
