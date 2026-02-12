package com.example.todolist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(TaskAlreadyCompletedException.class)
    public ResponseEntity<ErrorMessage> errorMessage() {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ErrorMessage("Task is already completed"));
    }

    @ExceptionHandler(TaskNotFoundByIdException.class)
    public ResponseEntity<ErrorMessage> noTask() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage("Task doesn't exists"));
    }


}
