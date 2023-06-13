package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public List<Course> searchCourses(String courseName, String instructor, String email) {
        if (courseName.isEmpty() && instructor.isEmpty() && email.isEmpty()) {
            return courseRepository.findAll(); 
        }
        return courseRepository.findByCourseNameContainingAndInstructorContainingAndEmailContaining(courseName, instructor, email);
    }
    

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
