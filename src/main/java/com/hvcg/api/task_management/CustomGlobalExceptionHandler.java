package com.hvcg.api.task_management;

import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
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
    
    @ExceptionHandler
    ResponseEntity handleNoObjectFound(HttpServerErrorException e) {
    	
    	return new ResponseEntity(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

	
}
