package com.ietpune.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ietpune.model.Paper;
import com.ietpune.model.PaperQuestion;
import com.ietpune.model.Subject;
import com.ietpune.service.FileService;
import com.ietpune.service.PaperService;
import com.ietpune.service.SubjectService;

@Controller
public class PaperController {
	@Autowired
	private SubjectService subjectService;

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
			if (file != null) {
				List<PaperQuestion> questions = fileService.fileToList(file);
				p.setQuesSetObj(questions);
				
				
			} else {
				System.out.println("file not get");
			}
			// p.setQuesSetObj(questions);
		} catch (IOException e) {
			System.out.println("File uploadr IOException:" + e.getMessage());
		}
		
		return "paper/addPaper";
	}
}
