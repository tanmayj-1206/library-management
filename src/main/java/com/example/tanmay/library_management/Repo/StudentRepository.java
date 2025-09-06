package com.example.tanmay.library_management.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tanmay.library_management.Model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
