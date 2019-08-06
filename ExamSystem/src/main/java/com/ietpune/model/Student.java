package com.ietpune.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studentId;

	@NotNull
	@Size(min = 2, message = "First name must be grater than two charector")
	@Size(max = 20, message = "First name must be less than twenty charector")
	@Pattern(regexp = "^[A-Z] {1}[a-z] {7,20}",message = "only charectors are allowed")
	private String firstName;

	@NotNull
	@Size(min = 2, message = "Last name must be grater than two charector")
	@Size(max = 20, message = "Last name must be less than twenty charector")
	@Pattern(regexp = "^[A-Z] {1}[a-z] {7,20}", message = "only charectors are allowed")
	private String lastName;

	@NotNull
	@Email(message = "Please enter valid email")
	private String emailId;

	@Size(min = 8, message = "Minimum 8 digit number")
	@Size(max = 12, message = "Maxmum 12 digit number")
	//@Positive(message = "Please enter valid number")
	private String prNo;

	@NotNull
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*])(?=.{8,})", message = "Please enter one upper latter, one lower latter, one special symbole and one digit is must in password")
	private String password;

	public Student() {
		// TODO Auto-generated constructor stub
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "StudentPaper", joinColumns = {
			@JoinColumn(referencedColumnName = "studentId", name = "studentId") }, inverseJoinColumns = {
					@JoinColumn(referencedColumnName = "paperId", name = "paperId") })
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

	public String getPrNo() {
		return prNo;
	}

	public void setPrNo(String prNo) {
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
