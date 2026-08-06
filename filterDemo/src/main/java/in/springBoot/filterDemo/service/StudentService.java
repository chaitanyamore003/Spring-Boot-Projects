package in.springBoot.filterDemo.service;

import in.springBoot.filterDemo.Dto.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public void createStudent(Student student){
        System.out.println("Student created successfully");
        System.out.println("Student email: " + student.getEmail());
        System.out.println("Student name: " + student.getName());

//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
