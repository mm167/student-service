package com.formation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private boolean active;
    private int grade;

    // constructor for first testing method
    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
