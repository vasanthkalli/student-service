package com.student.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mongodb.MongoException;
import com.student.exceptions.StudentNotFoundException;

@ControllerAdvice
public class StudentControllerAdvice {
    
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleRequestValidationExceptions(MethodArgumentNotValidException ex){
		  Map<String,String> errors=new HashMap<String,String>();
		ex.getBindingResult().getAllErrors().forEach(error->{
			 String fieldName = ((FieldError) error).getField();
		     String errorMessage = error.getDefaultMessage();
		     errors.put(fieldName, errorMessage);
		});
		return new ResponseEntity<Map<String,String>>(errors,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(MongoException.class)
	public ResponseEntity<Map<String,String>> handleMongoDBException(MongoException ex){
		  Map<String,String> errors=new HashMap<String,String>();
		  errors.put("Error", ex.getMessage());
		  return new ResponseEntity<Map<String,String>>(errors,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<Map<String,String>> handleStudentNotfoundException(StudentNotFoundException studentNotfoundException){
		  Map<String,String> errors=new HashMap<String,String>();
		  errors.put("error", studentNotfoundException.getMessage());
		  return new ResponseEntity<Map<String,String>>(errors,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String,String>> handleStudentNotfoundException(RuntimeException runtimeException){
		  Map<String,String> errors=new HashMap<String,String>();
		  errors.put("error", runtimeException.getMessage());
		  return new ResponseEntity<Map<String,String>>(errors,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	

}
