package com.siddu.springsecurity.controller;

import com.siddu.springsecurity.model.dao.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {


    private static final List<Student> STUDENT_LIST = Arrays.asList(
            new Student(1, "Siddhartha Chikkavarapu"),
            new Student(2, "Srinivas Vura"),
            new Student(3, "Manoj Kamma")
    );

    @GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId) {
        return STUDENT_LIST.stream()
                .filter(student -> student.getId().equals(studentId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Student " + studentId + " does not exists"));
    }
}
