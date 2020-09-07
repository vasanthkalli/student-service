package com.student.testutils;

import com.student.dto.StudentRequest;

public class TestUtils {

	public static StudentRequest createStudentRequest() {
		
		StudentRequest student=new StudentRequest();
		student.setAge(20);
		student.setGrade("B");
		student.setMarks(80);
		student.setName("name");
		return student;
	}
}
