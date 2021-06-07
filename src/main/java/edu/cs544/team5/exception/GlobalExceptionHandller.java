package edu.cs544.team5.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandller {

    @ExceptionHandler(StudentControllerException.class)
    public ResponseEntity<ErrorApi> handleStudentControllerException(StudentControllerException exception){

        ErrorApi errorApi = new ErrorApi();
        errorApi.setError("Error when calling StudentController");
        errorApi.setDetail(exception.getMessage());

        return ResponseEntity.badRequest().body(errorApi);
    }

}
