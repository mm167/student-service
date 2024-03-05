package com.formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.formation.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student getStudentByName(String name);

    @Query("SELECT AVG(s.grade) FROM Student s WHERE s.active = true")
    double getAverageGrade();
}
