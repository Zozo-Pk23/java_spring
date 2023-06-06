package com.example.demo;

import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;

@Controller
public class MainController {
    private final CourseService courseService;

    public MainController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        Course Course = new Course();
        model.addAttribute("course", Course);
        return "add";
    }

    @PostMapping("/save")
    public String saveCourse(@Valid @ModelAttribute("course") Course course,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add"; // Return to the form with validation errors
        } else {
            courseService.saveCourse(course);
            return "redirect:/"; // Redirect to a success page
        }
    }

    @GetMapping("/update/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        return "update";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable(value = "id") long id) {
        this.courseService.deleteCourse(id);
        return "redirect:/";
    }

}
