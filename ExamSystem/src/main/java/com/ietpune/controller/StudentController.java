package com.ietpune.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ietpune.model.Paper;
import com.ietpune.model.Student;
import com.ietpune.model.StudentPaper;
import com.ietpune.model.Subject;
import com.ietpune.service.FileService;
import com.ietpune.service.StudentPaperService;
import com.ietpune.service.StudentService;
import com.ietpune.service.SubjectService;

@Controller
@RequestMapping("/Student/")
public class StudentController {
	@Autowired private StudentService studentService;
	@Autowired private StudentPaperService studentPaperService;
	@Autowired private SubjectService subjectService;
	@Autowired private FileService fileService;
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
	@GetMapping("profile")
	public String forProfile(Model model,Principal principal) {
		Optional<Student> optStud=studentService.getStudentByPrn(principal.getName());
		if(optStud.isPresent()) {
			model.addAttribute("user", optStud.get());
		}else {
			return "redirect:/signout";
		}
		return "student/profile";
	}
	@GetMapping("editProfile")
	public String forEditProfile(Model model,Principal principal) {
		Optional<Student> optStud=studentService.getStudentByPrn(principal.getName());
		if(optStud.isPresent()) {
			model.addAttribute("command", optStud.get());
		}else {
			return "redirect:/signout";
		}
		return "student/editProfile";
	}
	@GetMapping("changePassword")
	public String forChangePassword(Model model,Principal principal) {
		Optional<Student> optStud=studentService.getStudentByPrn(principal.getName());
		if(optStud.isPresent()) {
			model.addAttribute("question", optStud.get().getSecurityQeustion().getQuestion());
		}
		return "student/changePassword";
	}
	@PostMapping("changePassword")
	public String forChangePasswordPost(Model model,@RequestParam("answer")String answer,@RequestParam("pass")String pass,@RequestParam("confPass")String confPass, Principal principal) {
		Optional<Student> optStud=studentService.getStudentByPrn(principal.getName());
		if(optStud.isPresent()) {
			Student student=optStud.get();
			if(student.getSecurityAnswer().equals(answer)) {
				if(pass.equals(confPass)) {
					student.setPassword(pass);
					studentService.changePass(student);
					model.addAttribute("msg","Password changed successfully...");
				}else {
					model.addAttribute("errmsg","Password and Conform password must same");
				}
			}else {
				model.addAttribute("errmsg","Wrong Answer...");
			}
		}
		return "student/changePassword";
	}
	@PostMapping("editProfile")
	public String forEditProfilePost(Model model,@ModelAttribute("command")Student stud ,Principal principal) {
		Optional<Student> optStud=studentService.getStudentByPrn(principal.getName());
		if(optStud.isPresent()) {
			Student student=optStud.get();
			student.setPrn(stud.getPrn());
			student.setFirstName(stud.getFirstName());
			student.setLastName(stud.getLastName());
			student=studentService.update(student);
			if(student==null) {
				model.addAttribute("errmsg","Error in updating information.");
			}else {
				model.addAttribute("msg","record updated.");
			}
		}else {
			return "redirect:/signout";
		}
		return "student/editProfile";
	}
	@PostMapping("uploadImg")
	public String forUploadImg(Model model,Principal principal, @RequestParam("photo") MultipartFile file) throws IOException {
		if(fileService.isValidImg(file)) {
			Optional<Student> optStud=studentService.getByPrn(principal.getName());
			if(optStud.isPresent()) {
				Student stud=optStud.get();
				stud.setImgUrl(fileService.saveImg(file,stud.getPrn()));
				studentService.update(stud);
			}
			return "redirect:/Student/profile";
		}
		model.addAttribute("errmsg", "Select jpg img only with less than 500kb size...");
		return "redirect:/Student/profile";
	}
}
