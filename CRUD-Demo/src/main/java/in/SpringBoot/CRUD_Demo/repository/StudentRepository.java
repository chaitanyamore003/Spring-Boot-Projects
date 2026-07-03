package in.SpringBoot.CRUD_Demo.repository;

import in.SpringBoot.CRUD_Demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

// Marks this class as a Spring Bean.
// Spring creates an object of this class and manages its lifecycle.
//
// NOTE:
// In a real Spring Boot application with Spring Data JPA,
// we usually use @Repository instead of @Component because
// @Repository is specifically meant for the persistence layer.
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}