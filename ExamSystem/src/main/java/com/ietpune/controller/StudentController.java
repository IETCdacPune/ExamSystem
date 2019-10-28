package com.ietpune.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Student")
public class StudentController {
	@GetMapping("")
	public String forStudentDashboardGet(){
		return "student/studentDashboard";
	}

}
