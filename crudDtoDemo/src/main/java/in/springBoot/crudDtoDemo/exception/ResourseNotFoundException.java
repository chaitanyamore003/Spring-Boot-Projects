package in.springBoot.crudDtoDemo.exception;


public class ResourseNotFoundException extends RuntimeException {
    public ResourseNotFoundException(String message) {
        super(message);
    }
}
