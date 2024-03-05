package com.formation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.formation.entity.Student;
import com.formation.repository.StudentRepository;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.Arrays;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void testGetStudentByName_returnsStudentDetails() {
        // given
        // Student savedStudent = studentRepository.save(new Student(null, "Mark"));
        Student savedStudent = testEntityManager.persistFlushFind(new Student(null, "Mark"));

        // when
        Student student = studentRepository.getStudentByName("Mark");

        // then
        then(student.getId()).isNotNull();
        then(student.getName()).isEqualTo(savedStudent.getName());

    }

    @Test
    void getAverageGrade_returnsAverageGrade() {
        // given
        Student mark = Student.builder().name("Mark").grade(90).active(true).build();
        Student paul = Student.builder().name("Paul").grade(80).active(false).build();
        Student watson = Student.builder().name("Watson").grade(70).active(true).build();

        Arrays.asList(mark, paul, watson).forEach(testEntityManager::persistFlushFind);

        // when
        double averageGrade = studentRepository.getAverageGrade();

        // then
        then(averageGrade).isEqualTo(80);
    }

}