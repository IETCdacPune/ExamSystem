package com.ietpune.controller;

import java.util.Optional;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ietpune.model.Course;
import com.ietpune.model.Student;
import com.ietpune.model.dto.StudentDTO;
import com.ietpune.service.CourseService;
import com.ietpune.service.QuestionService;
import com.ietpune.service.StudentService;

@Controller
public class UnauthenticatedController {
	private static final String ERRMSG = "errmsg";
	private static final String SIGNUP = "signup";
	@Autowired	StudentService studetService;
	@Autowired	QuestionService questionService;
	@Autowired CourseService courseService;

	@GetMapping(value = "/signout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/signin?logout=true";
	}

	@GetMapping("/signin")
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

	@GetMapping("/signup")
	public String forRegistration(Model model) {
		model.addAttribute("qList", questionService.getAllSecurityQuestion());
		model.addAttribute("command", new StudentDTO());
		return SIGNUP;
	}

	@PostMapping("/signup")
	public String forSingupPost(Model model, @Valid @ModelAttribute("command") StudentDTO studentDTO, BindingResult result) {
		if (result.hasErrors()) {
			return SIGNUP;
		}
		
		if (!studentDTO.getPassword().equals(studentDTO.getConformPass())) {
			model.addAttribute(ERRMSG, "Password and conform password not same...");
			return SIGNUP;
		}
		Optional<Course> optCourse = courseService.getCourseByCode(studentDTO.getCourseCode());
		if(!optCourse.isPresent()){
			model.addAttribute(ERRMSG, "Wrong course code not same...");
			return SIGNUP;
		}
		Student student=new Student();
		student.setFirstName(studentDTO.getFirstName());
		student.setLastName(studentDTO.getLastName());
		student.setEmailId(studentDTO.getEmailId());
		student.setPassword(studentDTO.getPassword());
		student.setPrn(studentDTO.getPrn());
		student.setCourse(optCourse.get());
		student.setSecurityQeustion(studentDTO.getSecurityQeustion());
		student.setSecurityAnswer(studentDTO.getSecurityAnswer());
		student = studetService.save(student);
		if (student == null) {
			model.addAttribute(ERRMSG, "Thier is an some error in registration...");
			return "signup?error";
		}
		model.addAttribute("msg", "You are register successfully...");
		model.addAttribute("command", new StudentDTO());
		return SIGNUP;

	}
}
