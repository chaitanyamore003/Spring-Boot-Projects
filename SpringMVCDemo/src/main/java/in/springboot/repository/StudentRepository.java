package in.springboot.repository;

import in.springboot.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repository Layer
 *
 * Responsibilities:
 * 1. Perform CRUD operations.
 * 2. Interact with the data source.
 *
 * In this project, we are using an in-memory HashMap
 * instead of a real database.
 *
 * Later this class can be replaced with:
 * - JDBC
 * - Hibernate
 * - Spring Data JPA
 */
@Repository
public class StudentRepository {

    /**
     * Simulated Database
     *
     * Key   -> Student ID
     * Value -> Student Object
     */
    private final Map<Long, Student> studentDB;

    /**
     * Constructor
     *
     * Initializes the in-memory database.
     */
    public StudentRepository() {
        studentDB = new HashMap<>();
    }

    /**
     * Saves a student.
     *
     * If the ID already exists,
     * the existing student will be overwritten.
     *
     * @param student Student to save.
     * @return Saved Student.
     */
    public Student save(Student student) {
        studentDB.put(student.getId(), student);
        return student;
    }

    /**
     * Finds a student by ID.
     *
     * @param id Student ID.
     * @return Student if found, otherwise null.
     */
    public Student findById(Long id) {
        return studentDB.get(id);
    }

    /**
     * Returns all students.
     *
     * @return List containing all students.
     */
    public List<Student> findAll() {
        return new ArrayList<>(studentDB.values());
    }
}