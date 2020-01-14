package com.ietpune.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ietpune.model.Paper;
import com.ietpune.model.Subject;
import com.ietpune.service.StudentService;
import com.ietpune.service.SubjectService;

@Controller



public class StudentController {
	@Autowired
	private StudentService studentService;
	
	private static final Logger log = LoggerFactory.getLogger(StudentController.class);

	@GetMapping("")
	public String forStudentDashboardGet(){
		
		return "student/studentDashboard";
	}
	
@GetMapping("student/newPapers")
	public String forNewPaperGet(Model model,Authentication authentication){
		//List<Subject> allSub=subjectService.getAllSubject();
		String prn=authentication.getName();
		List<Subject> allSub=studentService.getAllSubjectList(prn);
	
		log.debug("list.............."+allSub);
		if (!allSub.isEmpty()) {
			model.addAttribute("list", allSub);
		}
		return "student/allPaper";
	}


@GetMapping("student/oldPapers")
public String forOldPaperGet(Model model,Authentication authentication){
	//List<Subject> allSub=subjectService.getAllSubject();
	String prn=authentication.getName();
	List<Subject> allSub=studentService.getAllSubjectList(prn);

	log.debug("list.............."+allSub);
	if (!allSub.isEmpty()) {
		model.addAttribute("list", allSub);
	}
	return "student/allPaper";
}

@GetMapping("/student/startExam")
public String forStartExam(@ModelAttribute("command")Paper p)
{
	System.out.println("in..........................exam");
return "/student/startExam";	
}
}
