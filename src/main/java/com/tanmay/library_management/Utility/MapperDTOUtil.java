package com.tanmay.library_management.Utility;

import com.tanmay.library_management.DTO.BooksDTO;
import com.tanmay.library_management.DTO.StudentDTO;
import com.tanmay.library_management.DTO.UserDTO;
import com.tanmay.library_management.Model.Book;
import com.tanmay.library_management.Model.Student;
import com.tanmay.library_management.Model.UserModel;

public class MapperDTOUtil {

    public static StudentDTO toStudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getUser().getName());
        studentDTO.setNoOfBooksIssued(student.getBooksIssued()
                                        .stream()
                                        .filter(book -> !book.getIsReturned())
                                        .toList()
                                        .size()
                                    );
        studentDTO.setBooksIssued(
            student.getBooksIssued()
                .stream()
                .filter(book -> !book.getIsReturned())
                .map(book -> {
                    StudentDTO.IssuedBooks issuedBook = new StudentDTO.IssuedBooks();
                    issuedBook.setBookId(book.getBook().getId());
                    issuedBook.setBookTitle(book.getBook().getTitle());
                    return issuedBook;
                })
                .toList()
        );
        return studentDTO;
    }

    public static BooksDTO toBooksDTO(Book book) {
        BooksDTO booksDTO = new BooksDTO();
        booksDTO.setId(book.getId());
        booksDTO.setTitle(book.getTitle());
        booksDTO.setAuthor(book.getAuthor());
        booksDTO.setIsAvailable(book.getIsAvailable());
        book.getBooksIssued().stream()
            .filter(issuedBook -> !issuedBook.getIsReturned())
            .findFirst()
            .ifPresent(issuedBook -> {
                BooksDTO.IssuedTo issuedTo = new BooksDTO.IssuedTo();
                issuedTo.setStudentId(issuedBook.getStudent().getId());
                issuedTo.setStudentName(issuedBook.getStudent().getUser().getName());
                booksDTO.setIssuedTo(issuedTo);
            });

        return booksDTO;
    }

    public static UserDTO toUserDTO(UserModel user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setRole(user.getRole());
        userDTO.setStudent(user.getStudent() != null);
        userDTO.setUsername(user.getUsername());
        userDTO.setStudentId( user.getStudent() != null ? user.getStudent().getId() : 0);
        return userDTO;
    }
}
