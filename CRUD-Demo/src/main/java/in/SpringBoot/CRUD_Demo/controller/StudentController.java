package in.SpringBoot.CRUD_Demo.controller;

import in.SpringBoot.CRUD_Demo.entity.Student;
import in.SpringBoot.CRUD_Demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Marks this class as a REST Controller. It can handle HTTP requests and return JSON/text responses.
@RequestMapping("/api/students") // Base URL for all endpoints in this controller.
public class StudentController {

    // Reference to the Service layer.
    // The controller delegates business logic to this service instead of handling it directly.
    private StudentService studentService;

    // Constructor Injection:
    // Spring automatically creates a StudentService object and injects it here.
    // @Autowired tells Spring to resolve and provide the required dependency.
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Handles HTTP POST requests to:
    // POST /api/students/create
    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {

        // @RequestBody converts the incoming JSON request into a Student object.
        //
        // Example JSON:
        // {
        //   "name": "Rahul",
        //   "age": 21,
        //   "email": "rahul@gmail.com",
        //   "rollNo": 101,
        //   "subject": "Java"
        // }

        // Calls the createStudent() method in the Service layer.
        // The service performs the business logic and saves the student to the database.
        Student createdStudent = studentService.createStudent(student);

        // Sends a simple response back to the client after successful creation.
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }
}