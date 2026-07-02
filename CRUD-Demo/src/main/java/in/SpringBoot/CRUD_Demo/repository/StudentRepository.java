package in.SpringBoot.CRUD_Demo.repository;

import in.SpringBoot.CRUD_Demo.entity.Student;
import org.springframework.stereotype.Component;

// Marks this class as a Spring Bean.
// Spring creates an object of this class and manages its lifecycle.
//
// NOTE:
// In a real Spring Boot application with Spring Data JPA,
// we usually use @Repository instead of @Component because
// @Repository is specifically meant for the persistence layer.
@Component
public class StudentRepository {

    // This method is responsible for saving the Student object.
    // Currently, it is just a dummy implementation.
    public Student saveStudent(Student student) {

        // Here you would normally write the code to save the
        // student to the database.

        // For example:
        // entityManager.persist(student);
        // OR
        // studentJpaRepository.save(student);

        // Currently, this creates and returns a completely
        // new Student object.
        // It does NOT save the received student anywhere.
        return new Student();
    }
}