package com.student.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.student.dao.StudentDao;
import com.student.dto.Student;
import com.student.dto.StudentRequest;
import com.student.testutils.TestUtils;
@SpringBootTest
@RunWith(SpringRunner.class)
public class StudentServiceImplTest {
    
	@MockBean
	private  StudentDao studentDao;
	
	@Autowired
	private StudentServiceImpl studentServiceImpl;
	
	@Captor
	private ArgumentCaptor<StudentRequest> studentReqAargumentCaptor;
	
	@Test
	public void testCreateStudent() {
		
		studentServiceImpl.createStudent(TestUtils.createStudentRequest());
		verify(studentDao).createStudent(studentReqAargumentCaptor.capture());
		assertEquals(20,studentReqAargumentCaptor.getValue().getAge());
	}
	
	@Test
	public void testUpdateStudent() {
		Mockito.when(studentDao.updateStudent(any(StudentRequest.class))).thenReturn(Long.valueOf("4"));
		assertEquals(4,studentServiceImpl.updateStudent(TestUtils.createStudentRequest()));
	}
	
	@Test
	public void testSearchStudent() {
		Mockito.when(studentDao.findStudent(anyInt(), anyString())).thenReturn(Arrays.asList(new Student()));
		assertEquals(1,studentServiceImpl.searchStudent(22, "c").size());
	}

}
