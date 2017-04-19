package org.maxwell.springboot.http2.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentDetails {

	private int studentID;
	private String studentName;

	public StudentDetails() {
		// dummy constructor
	}

	public StudentDetails(int studentID, String studentName) {
		this.studentID = studentID;
		this.studentName = studentName;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

}
