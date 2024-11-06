package com.gestionale.web.app.gestionale_web.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String,String>> handleCustomException(CustomException ex){
        Map<String,String> body = new HashMap<>();
        body.put("message",ex.getMessage());
        body.put("status",ex.getStatusCode().name());  
        //body.put("parameter",ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(body);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String,Object>> handleTypeMisMatchException(MethodArgumentTypeMismatchException ex){
        
        Map<String,Object> exception = new HashMap<>();
        exception.put("status:",HttpStatus.BAD_REQUEST);
        exception.put("Expected:",ex.getRequiredType()); 
        exception.put("Parameter:",ex.getValue());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
    }
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Map<String,Object>> handleResourceFoundException(NoResourceFoundException ex){
        Map<String,Object> exception = new HashMap<>();
        exception.put("Parameter:","Your parameters are not ok!, check your inputs and try again!"); 
        exception.put("status:",HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
