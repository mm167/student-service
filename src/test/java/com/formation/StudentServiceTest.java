package com.formation;

import org.springframework.boot.test.context.SpringBootTest;

import com.formation.entity.Student;
import com.formation.exception.StudentNotFoundException;
import com.formation.repository.StudentRepository;
import com.formation.service.StudentService;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.transaction.Transactional;

@SpringBootTest(webEnvironment = NONE)
@Transactional
public class StudentServiceTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @DisplayName("Returning saved student from service layer")
    @Test
    void getStudentById_forSavedStudent_isReturned() {
        // given
        Student savedStudent = studentRepository.save(new Student(null, "Mark"));

        // when
        Student retrievedStudent = studentService.getStudentById(savedStudent.getId());

        // then
        then(retrievedStudent.getName()).isEqualTo("Mark");
        then(retrievedStudent.getId()).isNotNull();
    }

    @Test
    void getStudentById_whenMissingStudent_notFoundExceptionThrown() {
        // given
        Long id = 1234l;
        
        // when
        Throwable throwable = catchThrowable(() -> studentService.getStudentById(id));

        // then
        BDDAssertions.then(throwable).isInstanceOf(StudentNotFoundException.class);
    }
}
