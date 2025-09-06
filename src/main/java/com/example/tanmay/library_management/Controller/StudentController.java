package com.example.tanmay.library_management.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.tanmay.library_management.Model.Student;
import com.example.tanmay.library_management.Service.StudentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController("students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/getstudents")
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/addstudent")
    public String addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return "Student added successfully";
    }
    
    
}
