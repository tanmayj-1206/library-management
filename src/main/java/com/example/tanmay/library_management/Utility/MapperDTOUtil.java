package com.example.tanmay.library_management.Utility;

import com.example.tanmay.library_management.DTO.StudentDTO;
import com.example.tanmay.library_management.Model.Student;

public class MapperDTOUtil {
    public static <T, U> U map(T source, Class<U> destinationClass) throws Exception {
        U destination = destinationClass.getDeclaredConstructor().newInstance();
        org.springframework.beans.BeanUtils.copyProperties(source, destination);
        return destination;
    }

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
        studentDTO.setBookTitles(student.getBooksIssued()
                                    .stream()
                                    .filter(book -> !book.getIsReturned())
                                    .map(book -> book.getBook().getTitle())
                                    .toList()
                                );
        return studentDTO;
    }
}
