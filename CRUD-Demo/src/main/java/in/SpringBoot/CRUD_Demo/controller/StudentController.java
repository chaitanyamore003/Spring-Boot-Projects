package in.SpringBoot.CRUD_Demo.controller;

import in.SpringBoot.CRUD_Demo.entity.Student;
import in.SpringBoot.CRUD_Demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a REST Controller. It can handle HTTP requests and return JSON/text responses.
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Student requiredStudent =  studentService.getStudentById(id);
        if(requiredStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(requiredStudent);
    }


    @GetMapping("/getAll")
        public ResponseEntity<List<Student>> getAllStudents() {
            List<Student> listRes = studentService.getAllStudents();
            if(listRes == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(HttpStatus.OK).body(listRes);
        }


        @PutMapping("/update/{id}")
        public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student reqStudent) {
            Student updatedStudent = studentService.updateStudentById(id, reqStudent);
            if(updatedStudent == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(HttpStatus.OK).body(updatedStudent);
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<Boolean> deleteStudent(@PathVariable Long id) {
            Boolean deleted = studentService.deleteStudentById(id);

            if(deleted == false) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
}