package com.ietpune.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Student")
public class StudentController {
	@RequestMapping("")
	public String forStudentDashboardGet(){
		return "student/studentDashboard";
	}

}
