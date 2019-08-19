package com.ietpune.model;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Student extends User {
	
	@NotNull
	@Size(min = 2, message = "First name must be grater than two charector")
	@Size(max = 20, message = "First name must be less than twenty charector")
	@Pattern(regexp = "^[A-Z].[a-z]{2,20}",message = "only charectors are allowed")
	private String firstName;

	@NotNull
	@Size(min = 2, message = "Last name must be grater than two charector")
	@Size(max = 20, message = "Last name must be less than twenty charector")
	@Pattern(regexp = "^[A-Z].[a-z] {2,20}", message = "only charectors are allowed")
	private String lastName;

	@NotNull
	@Email(message = "Please enter valid email")
	private String emailId;

	public Student() {
		// TODO Auto-generated constructor stub
	}
	/*
	 * @ManyToMany(cascade = CascadeType.ALL)
	 * 
	 * @JoinTable(name = "StudentPaper", joinColumns = {
	 * 
	 * @JoinColumn(referencedColumnName = "studentId", name = "studentId") },
	 * inverseJoinColumns = {
	 * 
	 * @JoinColumn(referencedColumnName = "paperId", name = "paperId") }) private
	 * List<Paper> paperList;
	 */

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

}
