package com.ietpune.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CourseDTO {
	@NotNull
	@Size(min = 2, message = "Course name must be grater than two charector")
	@Size(max = 20, message = "Course name must be less than twenty charector")
	@Pattern(regexp = "[a-zA-Z]{2,20}",message = "only charectors are allowed")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
