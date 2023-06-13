package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService
 {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository StudentRepository) {
        this.studentRepository = StudentRepository;
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
