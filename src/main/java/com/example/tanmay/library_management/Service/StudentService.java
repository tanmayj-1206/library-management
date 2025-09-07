package com.example.tanmay.library_management.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.tanmay.library_management.Model.Student;
import com.example.tanmay.library_management.Model.UserModel;
import com.example.tanmay.library_management.Repo.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(UserModel student) {
        student.setRole("STUDENT");
        student.setPassword(encoder.encode(student.getPassword()));
        Student newStudent = new Student();
        newStudent.setUser(student);
        
        studentRepository.save(newStudent);
    }
}
