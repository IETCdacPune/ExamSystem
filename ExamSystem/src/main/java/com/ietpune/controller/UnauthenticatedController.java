package com.ietpune.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ietpune.model.Student;
import com.ietpune.service.StudentService;

@Controller
public class UnauthenticatedController {
	@Autowired StudentService studetservice;

	@RequestMapping(value = "/signout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/signin?logout=true";
	}

	@RequestMapping("/signin")
	public String forLogin(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model) {
		String errorMessge = null;
		if (error != null) {
			errorMessge = "Username or Password is incorrect !!";
		}
		if (logout != null) {
			errorMessge = "You have been successfully logged out !!";
		}
		model.addAttribute("errorMessge", errorMessge);
		return "signin";
	}

	@PostMapping("/signin")
	public String forLoginPost(Model model, @Valid @ModelAttribute("command") Student student, BindingResult result) {
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
	public String forSingupPost(Model model, @Valid @ModelAttribute("command") Student student, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("Student:- " + student);
			return "signup";
		}
		Student r = studetservice.save(student);
		if (r == null)
			return "signup";

		return "/Admin/";
	}
}
