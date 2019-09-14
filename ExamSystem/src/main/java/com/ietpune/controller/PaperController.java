package com.ietpune.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		List<Subject> sublist = subjectService.getAllSubject();
		model.addAttribute("sublist", sublist);
		model.addAttribute("command", new Paper());
		return "paper/addPaper";
	}

	@PostMapping("Admin/addPaper")
	public String forAddPaperPost(Model model, @ModelAttribute("command") Paper p,
			@RequestParam("file") MultipartFile file) {
		try {
				System.out.println(p);
				List<Question> questions = fileService.fileToList(file,p);
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
	
	@RequestMapping("/Admin/allPapers/{id}")
	public String forAllPaperGet(@PathVariable int id, Model model) {
		Subject sub=subjectService.getSubjectById(id);
		if(sub!=null) {
			List<Paper> subPaper = paperService.getAllPaperOfSubject(sub);
			model.addAttribute("list", subPaper);
		}else {
			model.addAttribute("errmsg","Please select valid subject");
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
}
