package in.SpringBoot.CRUD_Demo.service;

import in.SpringBoot.CRUD_Demo.entity.Student;
import in.SpringBoot.CRUD_Demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Marks this class as a Service component.
// Spring automatically detects it and creates a Bean (object) of this class.
// The Service layer contains the business logic of the application.
@Service
public class StudentService {

    // Reference to the Repository layer.
    // The service uses this object to interact with the database.
    private StudentRepository studentRepository;

    // Constructor Injection
    // Spring injects the StudentRepository Bean into this constructor.
    // This is the recommended way of Dependency Injection.
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Business method responsible for creating a new student.
    // It receives a Student object from the Controller.
    public Student createStudent(Student student) {

        // Calls the Repository layer to save the student in the database.
        // The saveStudent() method returns the saved Student object.
        // The returned object usually contains the generated ID as well.
        Student studentRes = studentRepository.saveStudent(student);

        // Returns the saved Student object back to the Controller.
        return studentRes;
    }
}