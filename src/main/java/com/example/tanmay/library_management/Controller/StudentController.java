package com.example.tanmay.library_management.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.tanmay.library_management.DTO.StudentDTO;
import com.example.tanmay.library_management.Model.UserModel;
import com.example.tanmay.library_management.Service.StudentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<StudentDTO> getStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/addstudent")
    public String addStudent(@RequestBody UserModel student) {
        studentService.addStudent(student);
        return "Student added successfully";
    }
    
    
}
