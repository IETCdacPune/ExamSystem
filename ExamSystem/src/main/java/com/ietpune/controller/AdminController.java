package com.ietpune.controller;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ietpune.model.Student;
import com.ietpune.service.StudentService;

@Controller

public class AdminController {
	@Autowired
	private StudentService studentService;
	Logger log= Logger.getLogger(AdminController.class);
	
	@GetMapping("Admin")
	public String forAdminDashboard() {
		return "admin/adminDashboard";
	}
	
	@GetMapping("Admin/listOfStudent")
	public String forListOfStudent(Model model) {
		
		System.out.println("welcomw.............");
		
		List<Student> studentAllList=studentService.getAllStudentList();
		log.info("list............."+studentAllList);
		
		model.addAttribute("studentAllList",studentAllList);
		return "admin/listOfStudent";
	}
	
}
