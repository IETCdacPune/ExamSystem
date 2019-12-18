package com.ietpune.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ietpune.model.Paper;
import com.ietpune.model.Subject;
import com.ietpune.service.StudentService;
import com.ietpune.service.SubjectService;

@Controller

public class StudentController {
	@Autowired
	private SubjectService subjectService;
	@GetMapping("")
	public String forStudentDashboardGet(){
		
		return "student/studentDashboard";
	}
	
	


	@GetMapping("student/allPapers")
	public String forAllPaperGet(Model model,HttpServletRequest request,HttpSession hs) {
		List<Subject> allSub=subjectService.getAllSubject();
		
		String coursesession=(String)request.getSession().getAttribute("course");
	//int courseId=	Integer.parseInt(coursesession);
	System.out.println("..............."+coursesession);
		//List<Subject> allSub =subjectService.getAllSubjectByCourse(courseId);
		
		
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
