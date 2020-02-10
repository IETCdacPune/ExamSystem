package com.ietpune.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ietpune.model.Paper;
import com.ietpune.model.Student;
import com.ietpune.model.StudentPaper;
import com.ietpune.model.Subject;
import com.ietpune.service.StudentPaperService;
import com.ietpune.service.StudentService;
import com.ietpune.service.SubjectService;

@Controller
@RequestMapping("/Student/")
public class StudentController {
	private static final Logger log = LoggerFactory.getLogger(StudentController.class);
	@Autowired private StudentService studentService;
	@Autowired private StudentPaperService studentPaperService;
	@Autowired private SubjectService subjectService;
	@GetMapping("/")
	public String forStudentDashboardGet() {

		return "student/dashboard";
	}

	@GetMapping("newPapers")
	public String forNewPaperGet(Model model, Authentication authentication) {
		List<Paper> allPaper = studentService.getAllNewPaperList(authentication.getName());
			model.addAttribute("list", allPaper);
		return "student/showPaper";
	}
	@GetMapping("result")
	public String forResult(Model model, Authentication authentication) {
		Optional<Student> optStud=studentService.getByPrn(authentication.getName());
		List<StudentPaper> list=null;
		List<Subject> subList = null;
		if(optStud.isPresent()) {
			list = studentPaperService.getAllPaper(optStud.get());
			subList = subjectService.getAllSubjectByCourse(optStud.get().getCourse());
		}
		else {
			return "redirect:/signout";
		}
		HashMap<Subject, List<StudentPaper>> map=new HashMap<Subject, List<StudentPaper>>();
		for(Subject sub:subList) {
			map.put(sub, list.stream()
			.filter((studentPaper)->studentPaper.getPaper().getSubject().getName().equals(sub.getName()))
			.collect(Collectors.toList()));
		}
		model.addAttribute("list", map);
		return "student/result";
	}
	@GetMapping("paperDetails/{studPaperId}")
	public String forDetailResult(Model model,@PathVariable int studPaperId){
		Optional<StudentPaper> optStudPaper=studentPaperService.getStudentPaper(studPaperId);
		if(optStudPaper.isPresent()) {
			StudentPaper sp=optStudPaper.get();
			model.addAttribute("studPaper", sp);
			model.addAttribute("attempt",sp.getStudentAnsMap().size());
			model.addAttribute("topFive",studentPaperService.getTopFiveStudentOfPaper(sp.getPaper()));
			return "student/detailResult";
		}
		return "redirect:student/result";
	}
	@GetMapping("AnswerSheet/{id}")
	public String forAnswerSheet(Model model,@PathVariable("id") int id,Principal principal) {
		Optional<StudentPaper> optStudPaper=studentPaperService.getStudentPaperWithQuestion(id);
		if(optStudPaper.isPresent()) {
			model.addAttribute("studPaper", optStudPaper.get());
		}
		return "student/answerSheet";
	}
	/*
	 * @GetMapping("student/oldPapers") public String forOldPaperGet(Model model,
	 * Authentication authentication) { // List<Subject>
	 * allSub=subjectService.getAllSubject(); String prn = authentication.getName();
	 * List<Paper> allSub = studentService.getAllSubjectList(prn); if
	 * (!allSub.isEmpty()) { model.addAttribute("list", allSub); } return
	 * "student/showPaper"; }
	 */

}
