package com.formation.service;

import java.util.List;

import com.formation.entity.Student;

public interface StudentService {

    public Student getStudentById(Long id) ; 
    public Student saveStudent(Student student) ;
    public List<Student> getAllStudents() ;
    
}
