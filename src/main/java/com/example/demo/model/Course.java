package com.example.demo.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_name", nullable = false)
    @NotBlank(message = "Course Name is required")
    private String courseName;

    @Column(name = "instructor", nullable = false)
    @NotBlank(message = "Instructor Name is required")
    private String instructor;

    @Column(name = "email", nullable = false)
    @NotBlank(message = "Email is required")
    private String email;
}
