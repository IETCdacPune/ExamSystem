package com.ietpune.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class StudentDTO {

	@NotNull
	@Pattern(regexp = "^[A-Z].[a-z]{2,20}", message = "only character are allowed")
	private String firstName;
	@NotNull
	@Pattern(regexp = "^[A-Z].[a-z]{2,20}", message = "only character are allowed")
	private String lastName;
	@NotNull
	private String courseCode;
	@NotNull
	@Email(message = "Please enter valid email")
	private String emailId;
	@Pattern(regexp = "^[0-9]{8,15}", message = "only digits are allowed")
	private String prn;
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&])[a-zA-Z0-9!@#$%^&]{8,}$", message = "password min length is 8 must containt atleast 1 Upper case, 1 Lower case, 1 digit, 1 !@#$%^&")
	private String password;
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&])[a-zA-Z0-9!@#$%^&]{8,}$", message = "password min length is 8 must containt atleast 1 Upper case, 1 Lower case, 1 digit, 1 !@#$%^&")
	private String conformPass;
	private char gender;
	@Min(value = 1,message = "select security question")
	private int sqId;
	private String securityAnswer;

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

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPrn() {
		return prn;
	}

	public void setPrn(String prn) {
		this.prn = prn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConformPass() {
		return conformPass;
	}

	public void setConformPass(String conformPass) {
		this.conformPass = conformPass;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getSqId() {
		return sqId;
	}

	public void setSqId(int sqId) {
		this.sqId = sqId;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

}
