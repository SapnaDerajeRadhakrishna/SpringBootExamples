package org.maxwell.springboot.http2.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TeacherDetails {

	private String teacherName;
	private int teacherID;

	public TeacherDetails(String teacherName, int teacherID) {
		this.teacherName = teacherName;
		this.teacherID = teacherID;
	}

	public TeacherDetails() {
		// default constructor
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public int getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}

}
