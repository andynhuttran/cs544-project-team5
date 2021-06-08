package edu.cs544.team5.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandller {

    @ExceptionHandler(StudentHandleException.class)
    public ResponseEntity<ErrorApi> handleStudentControllerException(StudentHandleException exception){
        ErrorApi errorApi = new ErrorApi();
        errorApi.setError("Error when calling StudentController");
        errorApi.setDetail(exception.getMessage());

        return ResponseEntity.status(exception.statusCode).body(errorApi);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorApi> handleGlobalException(Exception exception){
//        ErrorApi errorApi = new ErrorApi();
//        errorApi.setError("Error when calling APIs");
//        errorApi.setDetail(exception.getMessage());
//
//        return ResponseEntity.badRequest().body(errorApi);
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorApi> validationError(MethodArgumentNotValidException ex) {
        ErrorApi errorApi = new ErrorApi();
        errorApi.setError("Invalid parameters");

        final StringBuilder builder = new StringBuilder();
        BindingResult result = ex.getBindingResult();
        result.getFieldErrors().forEach(err -> {
            builder.append(err.getField() + " " + err.getDefaultMessage() + ",");
        });

        errorApi.setDetail(builder.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorApi);
    }

}
