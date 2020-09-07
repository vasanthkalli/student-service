package com.student.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoException;
import com.student.dto.Student;
import com.student.dto.StudentRequest;
import com.student.service.StudentService;
import com.student.testutils.TestUtils;
@WebMvcTest
@RunWith(SpringRunner.class)
public class StudentControllerTest {
	
    @Autowired
	private MockMvc mockMvc;
    
	@MockBean
	private StudentService studentService;
	
	@Captor
	private ArgumentCaptor<StudentRequest> studentRequestArgCap;

	@Test
	public void testCreateStudent() throws Exception {
		
		mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON_VALUE).
				content(new ObjectMapper().writeValueAsString(TestUtils.createStudentRequest()))).andExpect(status().isOk());
		verify(studentService).createStudent(studentRequestArgCap.capture());
		assertEquals("name",studentRequestArgCap.getValue().getName());
		assertEquals(20,studentRequestArgCap.getValue().getAge());
		assertEquals(80,studentRequestArgCap.getValue().getMarks());
		assertEquals("B",studentRequestArgCap.getValue().getGrade());
	}
	
	@Test
	public void testCreateStudentWithInvalidRequest() throws Exception {
		StudentRequest student=TestUtils.createStudentRequest();
		student.setGrade(null);
		mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON_VALUE).
				content(new ObjectMapper().writeValueAsString(student))).andExpect(status().isBadRequest());		
	}
	
	@Test
	public void testCreateStudentWithMongoError() throws Exception {
		doThrow(new MongoException("MongoWriteError")).when(studentService).createStudent(any(StudentRequest.class));
		mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON_VALUE).
				content(new ObjectMapper().writeValueAsString(TestUtils.createStudentRequest()))).andExpect(status().isInternalServerError());		
	}
	
	@Test
	public void testupdateStudent() throws Exception {
		
		when(studentService.updateStudent(any(StudentRequest.class))).thenReturn(Long.valueOf("2"));
		 MvcResult mvcResult= mockMvc.perform(
				put("/students").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString(TestUtils.createStudentRequest())))
		        .andExpect(status().isOk())
		        .andReturn();
		 assertEquals("2",mvcResult.getResponse().getContentAsString());
	}
	
	@Test
	public void testsearchStudent() throws Exception {
		
		Student student=new Student();
		student.setName("name");
		student.setGrade("B");
		List<Student> students=Arrays.asList(student,new Student());
		MultiValueMap<String, String> params=new LinkedMultiValueMap<String, String>();
		params.add("age", "22");
		params.add("grade", "B");
		when(studentService.searchStudent(Mockito.anyInt(),Mockito.anyString())).thenReturn(students);
		 mockMvc.perform(
				get("/students/search").contentType(MediaType.APPLICATION_JSON_VALUE)
				.queryParams(params)
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].grade",is("B")));
	}
	
	
}
