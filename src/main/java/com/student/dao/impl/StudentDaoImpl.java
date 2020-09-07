package com.student.dao.impl;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.student.dao.StudentDao;
import com.student.dto.Student;
import com.student.dto.StudentRequest;
import com.student.dto.User;
import com.student.exceptions.StudentNotFoundException;
import com.student.util.RequestUtils;

@Component
public class StudentDaoImpl implements StudentDao{
    
	@Autowired
	private MongoCollection<Student> mongoStudentCollection;
	
	@Autowired
	private MongoCollection<User> mongoUserDetailsCollection;
	 
	
	@Override
	public void createStudent(StudentRequest createStudentRequest) {
		Student student=RequestUtils.convertRequestToDataObject(createStudentRequest);
		mongoStudentCollection.insertOne(student);
	}
      /**
        *Update marks with matching grade 
        * 
       */
	@Override
	public long updateStudent(StudentRequest updateStudentRequest) {
		Bson filter=eq("grade",updateStudentRequest.getGrade());
		Bson newValue=new Document("marks",updateStudentRequest.getMarks());
		Bson updateOperationDocument=new Document("$set",newValue);
		return mongoStudentCollection.updateMany(filter, updateOperationDocument).getModifiedCount();
		
	}

	@Override
	public List<Student> findStudent(int age, String grade) {
		List<Student> studentList=new ArrayList<Student>();
		FindIterable<Student> iterableDocument=mongoStudentCollection.find(and(eq("age",age),eq("grade",grade)));
		MongoCursor<Student> students=iterableDocument.iterator();
		while(students.hasNext()) {
		 studentList.add(students.next());
		}
		if(studentList.size()>0)
			return studentList;
		else
			throw new StudentNotFoundException("No student found with given Age and Grade",404);
	}
	@Override
	public UserDetails getUserByUsername(String username) {
		User user=mongoUserDetailsCollection.find(eq("username",username)).first();
		return RequestUtils.formUserDetailsFromUser(user);
	}

}
