package com.ietpune.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {
	@Id
private int studentId;
private String studName;
private String emailId;
private long prNo;
private String password;
public Student() {
		// TODO Auto-generated constructor stub
	}


	public int getStudentId() {
		return studentId;
	}


	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}


	public String getStudName() {
		return studName;
	}


	public void setStudName(String studName) {
		this.studName = studName;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public long getPrNo() {
		return prNo;
	}


	public void setPrNo(long prNo) {
		this.prNo = prNo;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
