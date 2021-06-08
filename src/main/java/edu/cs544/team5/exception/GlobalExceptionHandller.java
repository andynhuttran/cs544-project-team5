package edu.cs544.team5.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandller {

    @ExceptionHandler(StudentControllerException.class)
    public ResponseEntity<ErrorApi> handleStudentControllerException(StudentControllerException exception) {

        ErrorApi errorApi = new ErrorApi();
        errorApi.setError("Error when calling StudentController");
        errorApi.setDetail(exception.getMessage());

        return ResponseEntity.badRequest().body(errorApi);
    }

    @ExceptionHandler(NoSuchRecordFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorApi> handleNoSuchElementFoundException(NoSuchRecordFoundException itemNotFoundException, WebRequest request) {
        return buildErrorResponse(itemNotFoundException, HttpStatus.NOT_FOUND);
    }


    private ResponseEntity<ErrorApi> buildErrorResponse(Exception exception,
                                                        HttpStatus httpStatus
    ) {
        ErrorApi errorResponse = new ErrorApi(Integer.toString(httpStatus.value()), exception.getMessage());
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

}
