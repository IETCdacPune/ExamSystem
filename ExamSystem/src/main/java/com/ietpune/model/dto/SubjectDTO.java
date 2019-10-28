package com.ietpune.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.ietpune.model.Course;

public class SubjectDTO {
	private int id;
	@Size(min=2,max=30,message = "subject lenght must in between 2 to 30 char...")
	@Pattern(regexp = "[a-zA-z -_]{2,30}" , message = "Please Enter Valide Name...")
	private String name;
	private Course course;
	
	public SubjectDTO() {
		super();
	}
	
	public SubjectDTO(@NotNull int id,
			@NotNull @Size(min = 2, max = 30, message = "subject lenght must in between 2 to 30 char...") @Pattern(regexp = "[a-zA-z -_]{2,15}", message = "Please Enter Valide Name...") String name,
			@NotNull Course course) {
		super();
		this.id = id;
		this.name = name;
		this.course = course;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
}
