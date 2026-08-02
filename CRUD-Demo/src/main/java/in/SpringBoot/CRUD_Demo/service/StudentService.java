package in.SpringBoot.CRUD_Demo.service;

import in.SpringBoot.CRUD_Demo.entity.Student;
import in.SpringBoot.CRUD_Demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Marks this class as a Service component.
// Spring automatically detects it and creates a Bean (object) of this class.
// The Service layer contains the business logic of the application.
@Service
public class StudentService {

    private final StudentRepository studentRepository;


    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student createStudent(Student student) {
        student.setDeleted(false);
        Student studentRes = studentRepository.save(student);
        return studentRes;
    }

    public Student getStudentById(Long id) {
        Optional<Student> studentRes = studentRepository.findByIdAndDeletedIsFalse(id);

        return studentRes.orElse(null);
    }

    public List<Student> getAllStudents() {
        List<Student> listRes = studentRepository.findAllByDeletedFalse();
        return listRes;
    }

    public Student updateStudentById(Long id, Student reqStudent) {
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);

        if(existingStudent.isEmpty()){
            return null;
        }

        Student studentToSave = existingStudent.get();
        studentToSave.setName(reqStudent.getName());
        studentToSave.setAge(reqStudent.getAge());
        studentToSave.setEmail(reqStudent.getEmail());
        studentToSave.setSubject(reqStudent.getSubject());
        studentToSave.setRollNo(reqStudent.getRollNo());

        return studentRepository.save(studentToSave);

    }

    public Boolean deleteStudentById(Long id) {
        Optional<Student> studentRes = studentRepository.findById(id);
        if(studentRes.isEmpty()){
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }

    public Boolean softDeleteStudentById(Long id) {
        Optional<Student> studentRes = studentRepository.findByIdAndDeletedIsFalse(id);
        if(studentRes.isEmpty()){ return false;}
        studentRes.get().setDeleted(true);
        studentRepository.save(studentRes.get());
        return true;
    }
}