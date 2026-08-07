package in.springBoot.SpringInterceptors.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @PostMapping("/add")
    public ResponseEntity<String> createStudent() {
        System.out.println("createStudent Controller called");
        return ResponseEntity.ok().body("Student added successfully");
    }
}
