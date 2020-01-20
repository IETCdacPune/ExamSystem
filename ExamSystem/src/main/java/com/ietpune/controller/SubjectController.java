package com.ietpune.controller;

import java.util.List;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ietpune.model.Subject;
import com.ietpune.model.dto.SubjectDTO;
import com.ietpune.service.CourseService;
import com.ietpune.service.SubjectService;

@Controller
@RequestMapping("Admin")
public class SubjectController {
	private static final String ERRMSG = "errmsg";
	private static final String COMMAND = "command";
	private static final String ADMIN_EDIT_SUBJECT = "admin/editSubject";
	private static final String ADMIN_ADD_SUBJECT = "admin/addSubject";
	@Autowired
	SubjectService subjectService;
	@Autowired
	CourseService courseService;
private Logger log =Logger.getLogger(SubjectController.class);
	@GetMapping("/addSubject")
	public String forAddSubjectGet(Model model) {
		model.addAttribute("courseList", courseService.getAllCourses());
		model.addAttribute(COMMAND, new SubjectDTO());
		return ADMIN_ADD_SUBJECT;
	}

	@GetMapping("/listOfSubject")
	public String forListOfSubjectGet(Model model) {
		model.addAttribute("list", subjectService.getAllSubject());
		return "admin/listOfSubject";
	}
	@GetMapping(value = "/subjectByCourse",produces = "application/json")
	public @ResponseBody List<Subject> forSubjectByCourseGet(@RequestParam(value = "course", required = true) String courseId){
		
		return subjectService.getAllSubjectByCourse(courseService.getCourseById(Integer.parseInt(courseId)).orElse(null));
	}
	@PostMapping("/addSubject")
	public String forAddSubjectPost(Model model, @Valid @ModelAttribute(COMMAND) SubjectDTO subjectDTO,
			BindingResult result) {
		if (result.hasErrors()) {
			return ADMIN_ADD_SUBJECT;
		}
		if (!subjectService.getSubjectByNameAndCourse(subjectDTO.getName().toUpperCase(), subjectDTO.getCourse())
				.isPresent()) {
			Subject subject = new Subject();
			subject.setName(subjectDTO.getName().toUpperCase());
			
			
			
			
			subject.setCourse(subjectDTO.getCourse());
			
			subject = subjectService.addSubject(subject);
			if (subject == null) {
				model.addAttribute(ERRMSG, "Thier is an error in adding subject...");
				return ADMIN_ADD_SUBJECT;
			}
			model.addAttribute("msg", "Subject Added successfully...");
			model.addAttribute("courseList", courseService.getAllCourses());
			model.addAttribute(COMMAND, new SubjectDTO());
			return ADMIN_ADD_SUBJECT;
		} else {
			model.addAttribute(ERRMSG, "Subject already exist...");
			return ADMIN_ADD_SUBJECT;
		}
	}

	@GetMapping("/subjectEdit/{id}")
	public String forSubjectEditGet(Model model, @PathVariable int id) {
		Subject subject = subjectService.getSubjectById(id);
		model.addAttribute(COMMAND, subject);
		return ADMIN_EDIT_SUBJECT;
	}

	@PostMapping("/subjectEdit/{id}")
	public String forSubjectEditPost(Model model, @PathVariable int id,
			@Valid @ModelAttribute(COMMAND) SubjectDTO subjectDTO, BindingResult result) {
		if (result.hasErrors()) {
			return ADMIN_EDIT_SUBJECT;
		}
		if (!subjectService.getSubjectByNameAndCourse(subjectDTO.getName().toUpperCase(), subjectDTO.getCourse())
				.isPresent()) {
			Subject subject = new Subject();
			subject.setId(subjectDTO.getId());
			subject.setName(subjectDTO.getName());
			subject.setCourse(subjectDTO.getCourse());
			subject = subjectService.addSubject(subject);
			if (subject == null) {
				model.addAttribute(ERRMSG, "Thier is an error in editing subject...");
				return ADMIN_EDIT_SUBJECT;
			}
			model.addAttribute("msg", "Subject edited successfully...");
			model.addAttribute(COMMAND, subject);
			return ADMIN_EDIT_SUBJECT;
		} else {
			model.addAttribute(ERRMSG, "Subject already exist...");
			return ADMIN_ADD_SUBJECT;
		}
	}
}
