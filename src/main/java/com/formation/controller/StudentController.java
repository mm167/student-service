package com.formation.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formation.entity.Student;
import com.formation.exception.StudentNotFoundException;
import com.formation.service.StudentService;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/")
    public String home() {
        return " <b>Welcome to Spring Boot REST V1</b>";
    }

    @GetMapping("/students/{id}")
    Student getStudent(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/students")
    List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/students")
    Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void studentNotFoundHandler(StudentNotFoundException e) {
    }

}
