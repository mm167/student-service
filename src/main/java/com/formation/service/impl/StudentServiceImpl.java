package com.formation.service.impl;

import com.formation.entity.Student;
import com.formation.exception.StudentNotFoundException;
import com.formation.repository.StudentRepository;
import com.formation.service.StudentService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public  class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    @Override
    @Cacheable("students")
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException());
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
