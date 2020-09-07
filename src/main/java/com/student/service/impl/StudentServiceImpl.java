package com.student.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.student.dao.StudentDao;
import com.student.dto.Student;
import com.student.dto.StudentRequest;
import com.student.service.StudentService;

@Component
public class StudentServiceImpl implements StudentService{
    
	@Autowired
	private StudentDao studentDao;
	
	@Override
	public void createStudent(StudentRequest createStudentRequest) {
		
		studentDao.createStudent(createStudentRequest);
	}

	@Override
	public long updateStudent(StudentRequest updateStudentRequest) {
		
		 return studentDao.updateStudent(updateStudentRequest);
	}

	@Override
	public List<Student> searchStudent(int age, String grade) {
		
		return studentDao.findStudent(age, grade);
	}

}
