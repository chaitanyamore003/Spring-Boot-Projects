package in.springBoot.crudDtoDemo.controller;

import in.springBoot.crudDtoDemo.dto.createStudent.CreateStudentRequestDto;
import in.springBoot.crudDtoDemo.dto.createStudent.CreateStudentResponseDto;
import in.springBoot.crudDtoDemo.dto.updateStudent.UpdateStudentRequestDto;
import in.springBoot.crudDtoDemo.dto.updateStudent.UpdateStudentResponseDto;
import in.springBoot.crudDtoDemo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students")
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //create or add students
    @PostMapping("/add")
    public ResponseEntity<CreateStudentResponseDto> addStudent(@Valid @RequestBody CreateStudentRequestDto student) {
        CreateStudentResponseDto studentRes = studentService.addStudent(student);

        return ResponseEntity.ok(studentRes);
    }

    //get student
    @GetMapping("/get/{id}")
    public ResponseEntity<CreateStudentResponseDto> getStudent(@PathVariable Long id) {
        CreateStudentResponseDto studentRes = studentService.getStudent(id);
        return ResponseEntity.ok(studentRes);
    }

    //update student
    @PostMapping("/update/{id}")
    public ResponseEntity<UpdateStudentResponseDto> updateStudent(@PathVariable Long id,@RequestBody UpdateStudentRequestDto studentRequestDto) {
        UpdateStudentResponseDto studentRes = studentService.updateStudent(id, studentRequestDto);
        return ResponseEntity.ok(studentRes);
    }

    //getAll students
    @GetMapping("/getAll")
    public ResponseEntity<List<CreateStudentResponseDto>> getAllStudents() {
        List<CreateStudentResponseDto> studentsList = studentService.getAllStudents();
        return ResponseEntity.ok(studentsList);
    }

    //delete student
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        Boolean deleted = studentService.deleteStudent(id);
        if(deleted){
            return ResponseEntity.ok("Student Data Deleted Successfully");
        }
        return ResponseEntity.notFound().build();
    }


    //soft-delete Student
    @PatchMapping("/soft-delete/{id}")
    public ResponseEntity<String> softDeleteStudent(@PathVariable Long id) {
        Boolean deleted = studentService.softDeleteStudent(id);
        if(deleted){
            return ResponseEntity.ok("Student Data Deleted Successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
