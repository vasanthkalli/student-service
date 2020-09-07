package com.student.util;

import org.bson.types.ObjectId;
import org.springframework.security.core.userdetails.UserDetails;

import com.student.dto.Student;
import com.student.dto.StudentRequest;
import com.student.dto.User;

public class RequestUtils {

	public static Student convertRequestToDataObject(StudentRequest createStudentRequest) {
		Student student=new Student();
		student.setAge(createStudentRequest.getAge());
		student.setGrade(createStudentRequest.getGrade());
		student.setMarks(createStudentRequest.getMarks());
		student.setName(createStudentRequest.getName());
		student.setId(new ObjectId().getTime());
		return student;
	}
	
	public static UserDetails formUserDetailsFromUser(User user) {
		UserDetails userDetails=new com.student.dto.UserDetails(user);
		return userDetails;
		
	}
	

}
