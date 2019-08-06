package com.ietpune.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ietpune.model.Subject;

@Controller
@RequestMapping("Admin")
public class AdminController {
	@RequestMapping("")
	public String forAdminDashboard() {
		return "admin/adminDashboard";
	}
	@RequestMapping("/listOfStudent")
	public String forListOfStudent() {
		return "admin/listOfStudent";
	}
	@RequestMapping("/addSubject")
	public String forAddSubjectGet(Model model) {
		model.addAttribute("command", new Subject());
		return "admin/addSubject";
	}
	@PostMapping("/addSubject")
	public String forAddSubjectPost(Model model,@Valid  @ModelAttribute("command") Subject subject, BindingResult result) {
		return "admin/addSubject";
	}

}
