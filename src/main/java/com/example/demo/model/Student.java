package com.example.demo.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MARK", nullable = false)
    @Min(value = 0, message = "Mark must be greater than or equal to 0")
    @Max(value = 100, message = "Mark must be less than or equal to 100")
    private int mark;

    @Column(name = "NAME", nullable = false)
    @NotBlank(message = "Student Name is required")
    private String studentName;

    @Column(name = "EMAIL", nullable = false)
    @NotBlank(message = "Student Email is required")
    private String studentEmail;

    @ManyToOne
    @JoinColumn(name = "COURSE_ID", referencedColumnName = "id")
    private Course course;
}
