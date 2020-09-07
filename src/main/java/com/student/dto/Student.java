package com.student.dto;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Student implements Serializable{

	private static final long serialVersionUID = 1L;
    
	private Long Id;
	
	private int age;
	
	private int marks;
	
	private String name;
	
	private String grade;
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String toString() {
		return "Id="+Id+"name="+name+"marks="+marks+"age="+age+"grade="+grade;
	}
}
