package in.springBoot.crudDtoDemo.service;

import in.springBoot.crudDtoDemo.dto.createStudent.CreateStudentRequestDto;
import in.springBoot.crudDtoDemo.dto.createStudent.CreateStudentResponseDto;
import in.springBoot.crudDtoDemo.dto.updateStudent.UpdateStudentRequestDto;
import in.springBoot.crudDtoDemo.dto.updateStudent.UpdateStudentResponseDto;
import in.springBoot.crudDtoDemo.entity.Student;
import in.springBoot.crudDtoDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    // Repository used to perform database operations.
    private final StudentRepository studentRepository;

    // Constructor Injection.
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Adds a new student to the database.
    public CreateStudentResponseDto addStudent(CreateStudentRequestDto studentReq) {

        // Convert request DTO into entity.
        Student student = mapToEntity(studentReq);

        // Save entity.
        Student savedStudent = studentRepository.save(student);

        // Convert entity into response DTO.
        CreateStudentResponseDto response = mapToDto(savedStudent);
        response.setMessage("Student successfully added");

        return response;
    }

    // Fetch student by ID.
    public CreateStudentResponseDto getStudent(Long id) {

        Student student = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElse(null);

        if (student == null) {
            CreateStudentResponseDto response = new CreateStudentResponseDto();
            response.setMessage("Student not found");
            return response;
        }

        CreateStudentResponseDto response = mapToDto(student);
        response.setMessage("Student successfully found");

        return response;
    }

    // Update an existing student.
    public UpdateStudentResponseDto updateStudent(Long id,
                                                  UpdateStudentRequestDto studentReq) {

        Student student = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElse(null);

        if (student == null) {
            UpdateStudentResponseDto response = new UpdateStudentResponseDto();
            response.setMessage("Student not found");
            return response;
        }

        // Update editable fields.
        student.setName(studentReq.getName());
        student.setAge(studentReq.getAge());
        student.setRollNo(studentReq.getRollNo());
        student.setSubject(studentReq.getSubject());

        // Save updated entity.
        Student updatedStudent = studentRepository.save(student);

        return mapToUpdateDto(updatedStudent, "Student updated successfully");
    }

    // Fetch all active students.
    public List<CreateStudentResponseDto> getAllStudents() {

        return studentRepository.findByDeletedIsFalse()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    // Permanently delete a student.
    public boolean deleteStudent(Long id) {

        Student student = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElse(null);

        if (student == null) {
            return false;
        }

        studentRepository.delete(student);
        return true;
    }

    // Soft delete a student.
    public boolean softDeleteStudent(Long id) {

        Student student = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElse(null);

        if (student == null) {
            return false;
        }

        student.setDeleted(true);
        studentRepository.save(student);

        return true;
    }

    // Converts Entity into Create Response DTO.
    private CreateStudentResponseDto mapToDto(Student student) {

        if (student == null) {
            return null;
        }

        CreateStudentResponseDto response = new CreateStudentResponseDto();

        response.setId(student.getId());
        response.setName(student.getName());
        response.setEmail(student.getEmail());
        response.setAge(student.getAge());
        response.setRollNo(student.getRollNo());
        response.setSubject(student.getSubject());

        response.setCreatedDate(student.getCreatedDate());
        response.setUpdatedDate(student.getUpdatedDate());

        return response;
    }

    // Converts Entity into Update Response DTO.
    private UpdateStudentResponseDto mapToUpdateDto(Student student,
                                                    String message) {

        UpdateStudentResponseDto response = new UpdateStudentResponseDto();

        if (student == null) {
            response.setMessage(message);
            return response;
        }


        response.setName(student.getName());
        response.setEmail(student.getEmail());
        response.setAge(student.getAge());
        response.setRollNo(student.getRollNo());
        response.setSubject(student.getSubject());

        response.setMessage(message);

        return response;
    }

    // Converts Create Request DTO into Entity.
    private Student mapToEntity(CreateStudentRequestDto studentReq) {

        Student student = new Student();

        student.setName(studentReq.getName());
        student.setEmail(studentReq.getEmail());
        student.setAge(studentReq.getAge());
        student.setRollNo(studentReq.getRollNo());
        student.setSubject(studentReq.getSubject());

        // New students are active by default.
        student.setDeleted(false);

        return student;
    }
}