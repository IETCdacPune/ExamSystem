package com.ietpune.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {
	@Id
	@GeneratedValue
private int studentId;
private String firstName;
private String lastName;
private String emailId;
private long prNo;
private String password;
public Student() {
		// TODO Auto-generated constructor stub
	}

@ManyToMany(cascade = CascadeType.ALL)
@JoinTable(name = "StudentPaper",joinColumns = {@JoinColumn(referencedColumnName = "studentId",name = "studentId")},inverseJoinColumns = {@JoinColumn(referencedColumnName = "paperId",name = "paperId")})
private Set<Paper> paperSetObj;

public int getStudentId() {
		return studentId;
	}


	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}


	


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
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


	public Set<Paper> getPaperSetObj() {
		return paperSetObj;
	}


	public void setPaperSetObj(Set<Paper> paperSetObj) {
		this.paperSetObj = paperSetObj;
	}


	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId="
				+ emailId + ", prNo=" + prNo + ", password=" + password + ", paperSetObj=" + paperSetObj + "]";
	}



	
	
	

}
