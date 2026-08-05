package in.springBoot.crudDtoDemo.service;

import in.springBoot.crudDtoDemo.dto.createStudent.CreateStudentRequestDto;
import in.springBoot.crudDtoDemo.dto.createStudent.CreateStudentResponseDto;
import in.springBoot.crudDtoDemo.dto.updateStudent.UpdateStudentRequestDto;
import in.springBoot.crudDtoDemo.dto.updateStudent.UpdateStudentResponseDto;
import in.springBoot.crudDtoDemo.entity.Student;
import in.springBoot.crudDtoDemo.exception.DuplicateResourceException;
import in.springBoot.crudDtoDemo.exception.ResourseNotFoundException;
import in.springBoot.crudDtoDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

        if(emailExists(studentReq)){
            throw new DuplicateResourceException("User with email " +  studentReq.getEmail() + " already exists");
        }

        Student studentToSave = mapToEntity(studentReq);
        CreateStudentResponseDto response =  mapToDto(studentRepository.save(studentToSave));
        return response;
    }

    // Fetch student by ID.
    public CreateStudentResponseDto getStudent(Long id) {

        Student studentRes = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourseNotFoundException("Student with id " + id + " not found"));

        return  mapToDto(studentRes);
    }

    // Update an existing student.
    public UpdateStudentResponseDto updateStudent(Long id,
                                                  UpdateStudentRequestDto studentReq) {

        Student student = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourseNotFoundException("Student with id " + id + " not found"));


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
    public void deleteStudent(Long id) {

        Student studentToBeDeleted = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourseNotFoundException("Student with id " + id + " not found"));

       studentRepository.delete(studentToBeDeleted);
    }

    // Soft delete a student.
    public void softDeleteStudent(Long id) {

        Student student = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourseNotFoundException("Student with id " + id + " not found"));

        student.setDeleted(true);
        studentRepository.save(student);
    }

    // Converts Entity into Create Response DTO.
    private CreateStudentResponseDto mapToDto(Student student) {

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
        student.setCreatedDate(LocalDate.now());
        student.setUpdatedDate(LocalDate.now());

        // New students are active by default.
        student.setDeleted(false);

        return student;
    }



    //checking if Email Already Exists
    private Boolean emailExists(CreateStudentRequestDto studentReq) {
        return studentRepository.existsByEmail(studentReq.getEmail());
    }
}