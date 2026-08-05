package in.springBoot.crudDtoDemo.dto.exception;

import java.time.LocalDateTime;

public class ExceptionResponseDto {

    private int statusCode;
    private String error;
    private String path;
    private LocalDateTime timestamp;
    private String message;

    public ExceptionResponseDto(int statusCode, String error, String path, LocalDateTime timestamp, String message) {
        this.statusCode = statusCode;
        this.error = error;
        this.path = path;
        this.timestamp = timestamp;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
