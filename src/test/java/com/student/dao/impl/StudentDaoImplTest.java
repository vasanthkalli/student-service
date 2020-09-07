package com.student.dao.impl;



import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bson.conversions.Bson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.UpdateResult;
import com.student.dto.Student;
import com.student.dto.StudentRequest;
import com.student.testutils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentDaoImplTest {
	
    @Autowired
	private StudentDaoImpl studentDaoImpl;
    @MockBean
    private MongoCollection<Student> mongoStudentCollection;
    @Captor
    private ArgumentCaptor<Student> argumentCaptor;
    
	@Test
	public void testCreateStudent() {
		StudentRequest createStudentRequest=TestUtils.createStudentRequest();
		studentDaoImpl.createStudent(createStudentRequest);
		verify(mongoStudentCollection).insertOne(argumentCaptor.capture());
		assertEquals(20,argumentCaptor.getValue().getAge());
	}
	
	@Test
	public void testupdateStudent() {
		StudentRequest updateStudentRequest=TestUtils.createStudentRequest();
		UpdateResult result=Mockito.mock(UpdateResult.class);
		when(result.getModifiedCount()).thenReturn(Long.valueOf("2"));
        when(mongoStudentCollection.updateMany(any(Bson.class),any(Bson.class))).thenReturn(result);
		assertEquals(2,studentDaoImpl.updateStudent(updateStudentRequest));
	}
	
	@Test
	public void testfindStudent() {
		FindIterable findIterable=Mockito.mock(FindIterable.class);
		MongoCursor mongoCursor=Mockito.mock(MongoCursor.class);
		when(findIterable.iterator()).thenReturn(mongoCursor);
		when(mongoStudentCollection.find(any(Bson.class))).thenReturn(findIterable);
		assertEquals(0,studentDaoImpl.findStudent(25, "A").size());
	
	}

}
