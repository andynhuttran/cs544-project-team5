package edu.cs544.team5.exception;

public class NoSuchRecordFoundException extends RuntimeException {
    public NoSuchRecordFoundException(String message) {
        super(message);
    }

    public NoSuchRecordFoundException() {
        this("No record available :(");
    }
}
