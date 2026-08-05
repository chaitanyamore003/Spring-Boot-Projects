package in.springBoot.crudDtoDemo.dto.updateStudent;

import jakarta.validation.constraints.*;

public class UpdateStudentRequestDto {


    private String name;

    @Positive(message = "Roll No cannot be Negative")
    private Integer rollNo;
    private String subject;

    @Min(value = 18, message = "Age should be at least 18")
    private int age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
