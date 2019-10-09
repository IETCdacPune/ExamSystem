package com.ietpune.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ietpune.exception.ExcelFileException;
import com.ietpune.model.Paper;
import com.ietpune.model.Question;
import com.ietpune.model.Subject;
import com.ietpune.service.FileService;
import com.ietpune.service.PaperService;
import com.ietpune.service.QuestionService;
import com.ietpune.service.SubjectService;

@Controller
public class PaperController {
	@Autowired
	private SubjectService subjectService;
	@Autowired private QuestionService questionService;
	@Autowired
	private PaperService paperService;
	@Autowired
	private FileService fileService;
	

	@RequestMapping("Admin/addPaper")
	public String forAddPaperGet(@ModelAttribute("command") Paper p, Model model) {
		model.addAttribute("sublist", subjectService.getAllSubject());
		model.addAttribute("command", new Paper());
		return "paper/addPaper";
	}

	@PostMapping("Admin/addPaper")
	public String forAddPaperPost(Model model, @ModelAttribute("command") Paper p,
			@RequestParam("file") MultipartFile file) {
		try {
				List<Question> questions = fileService.fileToList(file,p);
				if(questions==null || questions.isEmpty()) {
					model.addAttribute("errmsg", "Thier is an error in reading File...");
					return "paper/addPaper";
				}
				p.setQuestionList(questions);
				p=paperService.addPaper(p);	
				if(p==null) {
					model.addAttribute("errmsg", "Thier is an error in adding paper...");
					return "paper/addPaper";
				}
				model.addAttribute("msg", "Paper Added successfully...");
				List<Subject> sublist = subjectService.getAllSubject();
				model.addAttribute("sublist", sublist);
				model.addAttribute("command", new Paper());
				return "paper/addPaper";
		}catch(ExcelFileException e) {
			model.addAttribute("errmsg", e.getMessage());
			return "paper/addPaper";
		}
		catch (IOException e) {
			System.out.println("File uploadr IOException:" + e.getMessage());
			return "paper/addPaper";
		}
	}
	
	@RequestMapping("/Admin/allPapers")
	public String forAllPaperGet(Model model) {
		List<Subject> allSub=subjectService.getAllSubject();
		if(!allSub.isEmpty()) {
			model.addAttribute("list", allSub);
		}
		return "paper/allPaper";
	}
	@RequestMapping("/Admin/allQuestion/{id}")
	public String forAllQuestionGet(@PathVariable int id, Model model) {
		Paper paper=paperService.getPaperById(id);
		if(paper!=null) {
			List<Question> allQue = questionService.getAllQuestionOfPaper(paper);
			model.addAttribute("list", allQue);
		}else {
			model.addAttribute("errmsg","Please select valid paper");
		}
		return "paper/allQuestion";
	}
	
	@RequestMapping("/Admin/questionEdit/{id}")
	public String forQuestionEdit(@PathVariable int id, Model model) {
		Question question=questionService.getQuestionById(id);
		if(question!=null) {
			model.addAttribute("command", question);
			return "paper/questionEdit";
		}else {
			model.addAttribute("errmsg","Please select valid question");
		}
		return "paper/allQuestion";
	}
	@PostMapping("/Admin/questionEdit/{id}")
	public String forQuestionEditPost(@PathVariable int id, Model model,@Valid @ModelAttribute("command") Question question, BindingResult result) {
		if (result.hasErrors()) {
			return "paper/questionEdit";
		}
		question=questionService.editQuestion(question);
		if(question==null) {
			model.addAttribute("errmsg", "Thier is an error in edittig question...");
			return "paper/questionEdit";
		}
		model.addAttribute("msg", "Subject Added successfully...");
		model.addAttribute("command", question);
		return "paper/questionEdit";
	}
}
