package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.service.CourseService;
import com.example.demo.service.StudentService;

import jakarta.validation.Valid;

@Controller
public class StudentController {
    private final StudentService studentService;
    private final CourseService courseService;
    private List<Course> courses; 

    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.courses = courseService.getAllCourses();
    }

    @GetMapping("/student/index")
    public String index(@RequestParam(value = "studentName", defaultValue = "") String studentName,
            @RequestParam(value = "studentEmail", defaultValue = "") String studentEmail,
            @RequestParam(value = "courseName", defaultValue = "") String courseName,
            @RequestParam(value = "mark_start", required = false) Integer markStart,
            @RequestParam(value = "mark_end", required = false) Integer markEnd, Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        model.addAttribute("courses", courses); 
        return "students/index";
    }

    @GetMapping("/student/add")
    public String add(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("courses", courses); 
        return "students/add";
    }

    @PostMapping("/student/save")
    public String save(@Valid @ModelAttribute("student") Student student,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("courses", courses); 
            return "students/add";
        } else {
            studentService.saveStudent(student);
            return "redirect:/student/index";
        }
    }

    @GetMapping("/student/update/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        model.addAttribute("courses", courses); 
        return "students/update";
    }

    @GetMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable(value = "id") long id) {
        this.studentService.deleteStudent(id);
        return "redirect:/student/index";
    }

}
