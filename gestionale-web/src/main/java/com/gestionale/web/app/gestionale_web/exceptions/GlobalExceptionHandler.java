package com.gestionale.web.app.gestionale_web.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String,Object>> handleCustomException(CustomException ex){
        Map<String,Object> exception = new HashMap<>();
        exception.put("status:",ex.getStatusCode());  
        exception.put("parameter:",ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(exception);
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
}
