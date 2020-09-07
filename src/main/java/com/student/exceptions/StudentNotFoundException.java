package com.student.exceptions;

import java.io.Serializable;

public class StudentNotFoundException extends RuntimeException implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String message;
	private int errorCode;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public StudentNotFoundException(){
		
	}	
	public StudentNotFoundException(String message,int errorCode){
		this.message=message;
		this.errorCode=errorCode;
	}
}
