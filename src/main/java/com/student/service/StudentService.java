package com.student.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.student.dto.Student;
import com.student.dto.StudentRequest;

@Service
public interface StudentService {

	public void createStudent(StudentRequest createStudentRequest);
	
	public long updateStudent(StudentRequest updateStudentRequest);
	
	public List<Student> searchStudent(int age,String grade);
}
