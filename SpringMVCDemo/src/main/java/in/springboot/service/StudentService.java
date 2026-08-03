package in.springboot.service;

import in.springboot.entity.Student;
import in.springboot.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Layer
 *
 * Responsibilities:
 * 1. Contains business logic.
 * 2. Acts as a bridge between Controller and Repository.
 * 3. Performs validations and processing before accessing the database.
 *
 * Flow:
 * Controller --> Service --> Repository --> Database
 */
@Service
public class StudentService {

    /**
     * Repository dependency.
     * Responsible for performing CRUD operations.
     */
    private final StudentRepository studentRepository;

    /**
     * Constructor Injection.
     *
     * Spring automatically injects StudentRepository because
     * there is only one constructor.
     */
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Creates a new Student.
     *
     * @param student Student object received from the Controller.
     * @return Saved Student object.
     */
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    /**
     * Retrieves a Student by its ID.
     *
     * @param id Student ID.
     * @return Student if found, otherwise null.
     */
    public Student getStudent(Long id) {
        return studentRepository.findById(id);
    }

    /**
     * Retrieves all Students.
     *
     * @return List of all students.
     */
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}