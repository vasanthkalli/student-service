package com.student.dao;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.student.dto.Student;
import com.student.dto.StudentRequest;

@Repository
public interface StudentDao {
	
	public void createStudent(StudentRequest createStudentRequest);
	
	public long updateStudent(StudentRequest updateStudentRequest);
	
	public List<Student> findStudent(int age,String grade);
	
	public UserDetails getUserByUsername(String username);
}
