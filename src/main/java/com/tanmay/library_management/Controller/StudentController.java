package com.tanmay.library_management.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.tanmay.library_management.DTO.APIResponseWrapper;
import com.tanmay.library_management.DTO.StudentDTO;
import com.tanmay.library_management.Model.UserModel;
import com.tanmay.library_management.Service.StudentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/getstudents")
    public ResponseEntity<APIResponseWrapper<List<StudentDTO>>> getStudents() {
        return ResponseEntity.ok(APIResponseWrapper.success("Students retrieved successfully", studentService.getAllStudents()));
    }

    @PostMapping("/addstudent")
    public ResponseEntity<APIResponseWrapper<String>> addStudent(@RequestBody UserModel student) {
        studentService.addStudent(student);
        return ResponseEntity.ok(APIResponseWrapper.success("Student added successfully", null));
    }
    
}
