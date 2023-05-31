package com.example.studentmanager.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studentmanager.models.Student;


public interface StudentRepository extends JpaRepository<Student, Integer>{
	Student findByStudentNumber(String studentNumber);
}