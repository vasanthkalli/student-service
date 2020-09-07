package com.student.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class StudentRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message="Name Should not be Empty ")
	@Size(min=1,max=15,message="Name should be maximum 15 and minimum 1 characters")
	private String name;
    @NotNull(message="Marks Should not be Empty")
    @Min(value = 0,message="marks should not be less than zero")
    @Max(value=100,message="marks should not be more than 100")
	private int marks;
    @NotNull(message="Age Should not be Empty")
	private int age;
	@NotBlank(message="Grade Should not be Empty ")
	@Size(min = 1,max=1,message="Grade should have only one character")
	private String grade;

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
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
	

}
