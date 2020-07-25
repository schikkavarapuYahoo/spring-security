package com.siddu.springsecurity.controller;

import com.siddu.springsecurity.model.dao.Student;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management/api/v1/students")
public class ManagementController {

    private static final List<Student> STUDENT_LIST = Arrays.asList(
            new Student(1, "Siddhartha Chikkavarapu"),
            new Student(2, "Srinivas Vura"),
            new Student(3, "Manoj Kamma")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GA')")
    public List<Student> getAllStudents(){
        return STUDENT_LIST;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void registerNewStudent(@RequestBody Student student){
        System.out.println(student);
    }

    @DeleteMapping(path = "{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable Integer id){
        System.out.println("=====> student id: "  + id);
    }

    @PutMapping(path = "{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable Integer id, @RequestBody Student student){
        System.out.println(String.format("%s %s", student.getId().toString(), student.getName()));
        System.out.println("=====> student id: "  + id);
    }

    @GetMapping(path = "{studentId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GA')")
    public Student getStudent(@PathVariable("studentId") Integer studentId) {
        return STUDENT_LIST.stream()
                .filter(student -> student.getId().equals(studentId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Student " + studentId + " does not exists"));
    }
}
