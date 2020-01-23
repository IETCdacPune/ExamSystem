package com.ietpune.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ietpune.model.Paper;
import com.ietpune.model.Subject;
import com.ietpune.service.StudentService;

@Controller

public class StudentController {
	private static final Logger log = LoggerFactory.getLogger(StudentController.class);
	@Autowired
	public StudentService studentService;

	@GetMapping("")
	public String forStudentDashboardGet() {

		return "student/studentDashboard";
	}

	@GetMapping("student/newPapers")
	public String forNewPaperGet(Model model, Authentication authentication) {
		// List<Subject> allSub=subjectService.getAllSubject();
		String prn = authentication.getName();
		List<Paper> allSub = studentService.getAllSubjectList(prn);

		log.debug("list.............." + allSub);
		if (!allSub.isEmpty()) {
			model.addAttribute("list", allSub);
		}
		return "student/showPaper";
	}

	@GetMapping("student/oldPapers")
	public String forOldPaperGet(Model model, Authentication authentication) {
		// List<Subject> allSub=subjectService.getAllSubject();
		String prn = authentication.getName();
		List<Paper> allSub = studentService.getAllSubjectList(prn);
		if (!allSub.isEmpty()) {
			model.addAttribute("list", allSub);
		}
		return "student/showPaper";
	}

}
