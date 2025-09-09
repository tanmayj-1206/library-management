package com.tanmay.library_management.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanmay.library_management.Model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findByUserUsername(String username);
}
