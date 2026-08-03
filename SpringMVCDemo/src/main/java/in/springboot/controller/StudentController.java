package in.springboot.controller;

import in.springboot.entity.Student;
import in.springboot.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller responsible for handling all Student-related HTTP requests.
 *
 * Base URL:
 *      /students
 */
@RestController
@RequestMapping("/students")
public class StudentController {

    /**
     * Service layer dependency.
     * Handles business logic related to Student.
     */
    private final StudentService studentService;

    /**
     * Constructor Injection.
     *
     * Spring automatically injects StudentService because
     * there is only one constructor.
     */
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Create a new Student.
     *
     * URL:
     * POST /students
     *
     * Request Body:
     * {
     *     "id":1,
     *     "name":"John",
     *     "age":20
     * }
     */
    @PostMapping("/add")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {

        Student createdStudent = studentService.createStudent(student);

        if (createdStudent == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    /**
     * Get Student by ID.
     *
     * URL:
     * GET /students/{id}
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") Long id) {

        Student student = studentService.getStudent(id);

        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    /**
     * Get all Students.
     *
     * URL:
     * GET /students
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudents() {

        List<Student> students = studentService.getAllStudents();

        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}