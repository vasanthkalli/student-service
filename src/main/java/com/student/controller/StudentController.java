package com.student.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.dto.AuthenticationRequest;
import com.student.dto.Student;
import com.student.dto.StudentRequest;
import com.student.service.StudentService;
import com.student.util.JWTUtils;

@RestController
public class StudentController {
	Logger logger=LoggerFactory.getLogger(StudentController.class);
	@Autowired
	private StudentService studentService;
	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JWTUtils jwtUtils;
	
	@PostMapping(value="/students")
	public void createStudent(@Valid @RequestBody StudentRequest createStudentRequest) throws JsonProcessingException {
		logger.info("Request Body Recieved  {}",mapper.writeValueAsString(createStudentRequest));
		
		studentService.createStudent(createStudentRequest);
	}

	@PutMapping(value="/students")
	public long updateStudent(@RequestBody StudentRequest updateStudentRequest) throws JsonProcessingException {
	logger.info("Request Body Recieved  {}",mapper.writeValueAsString(updateStudentRequest));
	   return studentService.updateStudent(updateStudentRequest);
		
	}
	
	@GetMapping(value="/students/search")
	public List<Student> searchStudent(@RequestParam("age") int age,@RequestParam("grade") String grade) {
		logger.info("age {} and grade {} values received",age,grade);
		List<Student> students=studentService.searchStudent(age, grade);
		return students;
	}
	
	@PostMapping(value = "/auth")
	public String generateJWT(@RequestBody AuthenticationRequest authRequest) {
			Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getPassword()));
		    SecurityContextHolder.getContext().setAuthentication(authentication);
		    return jwtUtils.generateToken(authentication);
	}
}
