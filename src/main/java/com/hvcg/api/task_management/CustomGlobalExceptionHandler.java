package com.hvcg.api.task_management;

import java.io.IOException;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	
    @ExceptionHandler
    ResponseEntity handleConstrantViolation(DataIntegrityViolationException e) {
    	
    	return new ResponseEntity("Database Constraint violation", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler
    ResponseEntity handleNoObjectFound(NoSuchElementException e) {
    	
    	return new ResponseEntity("No Object can be found ", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    

	
}
