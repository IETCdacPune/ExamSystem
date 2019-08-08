package com.ietpune.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ietpune.model.Subject;
import com.ietpune.service.SubjectService;

@Controller
@RequestMapping("Admin")
public class AdminController {
	@Autowired SubjectService subjectService;
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
		if (result.hasErrors()) {
		return "admin/addSubject";
		}
		subject=subjectService.addSubject(subject);
		if(subject==null) {
			model.addAttribute("errmsg", "Thier is an error in adding subject...");
			return "admin/addSubject";
		}
		model.addAttribute("msg", "Subject Added successfully...");
		model.addAttribute("command", new Subject());
		return "admin/addSubject";
	}

	@RequestMapping("/listOfSubject")
	public String forListOfSubjectGet(Model model) {
		model.addAttribute("list", subjectService.getAllSubject());
		return "admin/listOfSubject";
	}
	@RequestMapping("/subjectEdit/{id}")
	public String forSubjectEditGet(Model model,@PathVariable int id) {
		Subject subject=subjectService.getSubjectById(id);
		model.addAttribute("command", subject);
		return "admin/editSubject";
	}
	@PostMapping("/subjectEdit/{id}")
	public String forSubjectEditPost(Model model,@PathVariable int id,@Valid  @ModelAttribute("command") Subject subject, BindingResult result) {
		if (result.hasErrors()) {
			return "admin/editSubject";
		}
		subject=subjectService.addSubject(subject);
		if(subject==null) {
			model.addAttribute("errmsg", "Thier is an error in editing subject...");
			return "admin/editSubject";
		}
		model.addAttribute("msg", "Subject edited successfully...");
		model.addAttribute("command", new Subject());
		return "admin/editSubject";
	}
}
