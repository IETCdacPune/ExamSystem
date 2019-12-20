package com.ietpune.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Student extends User {

	private String firstName;
	private String lastName;
	private String emailId;
	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private Course course;
	@ManyToOne
	@JoinColumn
	private SecurityQuestion securityQeustion;
	private String securityAnswer;
	@OneToMany
	private List<Student_Paper> student_Paperslist; 

	public Student() {
		super();

	}

	public Student(User user) {
		super(user);
	}

	public Student(User user,
			@NotNull @Size(min = 2, message = "First name must be grater than two charector") @Size(max = 20, message = "First name must be less than twenty charector") @Pattern(regexp = "^[A-Z].[a-z]{2,20}", message = "only charectors are allowed") String firstName,
			@NotNull @Size(min = 2, message = "Last name must be grater than two charector") @Size(max = 20, message = "Last name must be less than twenty charector") @Pattern(regexp = "^[A-Z].[a-z] {2,20}", message = "only charectors are allowed") String lastName,
			@NotNull @Email(message = "Please enter valid email") String emailId) {
		super(user);
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public SecurityQuestion getSecurityQeustion() {
		return securityQeustion;
	}

	public void setSecurityQeustion(SecurityQuestion securityQeustion) {
		this.securityQeustion = securityQeustion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public List<Student_Paper> getStudent_Paperslist() {
		return student_Paperslist;
	}

	public void setStudent_Paperslist(List<Student_Paper> student_Paperslist) {
		this.student_Paperslist = student_Paperslist;
	}

	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId + ", course="
				+ course + ", securityQeustion=" + securityQeustion + ", securityAnswer=" + securityAnswer
				+ ", student_Paperslist=" + student_Paperslist + "]";
	}

}
