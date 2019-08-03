package com.ietpune.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ietpune.model.Student;

@Controller
public class UnauthenticatedController {
	@RequestMapping("/signin")
	public String forLogin(Model model) {
		model.addAttribute("command", new Student());
		return "signin";
	}
	@PostMapping("/signin")
	public String forLoginPost(Model model,@Valid  @ModelAttribute("command") Student student, BindingResult result) {
		if (result.hasErrors()) {
			return "signin";
		}
		return "/Admin/";
	}

	@RequestMapping("/signup")
	public String forRegistration(Model model) {
		model.addAttribute("command", new Student());
		return "signup";
	}

	@PostMapping("/signup")
	public String forSingupPost(Model model, @Valid  @ModelAttribute("command") Student student, BindingResult result) {
		if (result.hasErrors()) {
			return "signup";
		}
		return "/Admin/";
	}

}
