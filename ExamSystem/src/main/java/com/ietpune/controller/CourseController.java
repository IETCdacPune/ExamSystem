package com.ietpune.controller;

import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ietpune.model.Course;
import com.ietpune.model.Subject;
import com.ietpune.model.dto.CourseDTO;
import com.ietpune.service.CourseService;
@Controller
@RequestMapping(value = "Admin")
public class CourseController {
	private static final String COMMAND = "command";
	private static final String ADMIN_ADD_COURSE = "course/addCourse";
	private static final String ADMIN_LIST_COURSES = "course/listOfCourses";
	
	@Autowired CourseService courseService;
	
	@GetMapping("/addCourse")
	public String forAddSubjectGet(Model model) {
		model.addAttribute(COMMAND, new Course());
		return ADMIN_ADD_COURSE;
	}
	@PostMapping("/addCourse")
	public String forAddSubjectPost(Model model,@Valid  @ModelAttribute(COMMAND) CourseDTO courseDTO, BindingResult result) {
		if (result.hasErrors()) {
		return ADMIN_ADD_COURSE;
		}
		Course course=new Course();
		course.setName(courseDTO.getName().toUpperCase());
		course.setCourseCode(courseService.getCourseCode());
		try {
		course=courseService.addCourse(course);
		}catch(SQLException ex) {
			model.addAttribute("errmsg", "Course is already added...");
			return ADMIN_ADD_COURSE;
		}
		if(course==null) {
			model.addAttribute("errmsg", "Thier is an error in adding course...");
			return ADMIN_ADD_COURSE;
		}
		model.addAttribute("msg", "Course added successfully...");
		model.addAttribute(COMMAND, new Subject());
		return ADMIN_ADD_COURSE;
	}
	@GetMapping("/listOfCourses")
	public String forListOfCoursesGet(Model model) {
		model.addAttribute("list", courseService.getAllCoursesWithSubject());
		return ADMIN_LIST_COURSES;
	}
}
