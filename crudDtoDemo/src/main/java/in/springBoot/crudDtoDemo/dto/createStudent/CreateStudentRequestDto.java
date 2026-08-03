package in.springBoot.crudDtoDemo.dto.createStudent;

import jakarta.validation.constraints.*;

public class CreateStudentRequestDto {

    @NotBlank(message = "Name cannot be Null")
    @Size(min = 2, max = 50, message = "Student name must be within 2 to 50 characters long")
    private String name;

    @Email(message = "Enter a valid Email Address")
    private String email;

    @NotEmpty(message = "Roll no cannot be Null")
    private Integer rollNo;

    @NotBlank(message = "Subject must be defined")
    private String subject;

    @NotNull(message = "Age cannot be null")
    @Min(value = 18, message = "Age should be atleast 18")
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
