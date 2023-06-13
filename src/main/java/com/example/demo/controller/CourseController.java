package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import com.example.demo.model.Course;
import com.example.demo.service.CourseService;

import java.util.List;

@Controller
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/")
    public String searchCourses(
            @RequestParam(value = "courseName", required = false, defaultValue = "") String courseName,
            @RequestParam(value = "instructor", required = false, defaultValue = "") String instructor,
            @RequestParam(value = "email", required = false, defaultValue = "") String email,
            Model model) {
        List<Course> searchResults = courseService.searchCourses(courseName, instructor, email);
        model.addAttribute("courses", searchResults);
        return "courses/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        Course Course = new Course();
        model.addAttribute("course", Course);
        return "courses/add";
    }

    @PostMapping("/save")
    public String saveCourse(@Valid @ModelAttribute("course") Course course,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "courses/add"; // Return to the form with validation errors
        } else {
            courseService.saveCourse(course);
            return "redirect:/"; // Redirect to a success page
        }
    }

    @GetMapping("/update/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        return "courses/update";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable(value = "id") long id) {
        this.courseService.deleteCourse(id);
        return "redirect:/";
    }

}
