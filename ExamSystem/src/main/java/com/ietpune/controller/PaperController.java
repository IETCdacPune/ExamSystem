package com.ietpune.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ietpune.model.Paper;
import com.ietpune.model.Subject;
import com.ietpune.service.SubjectService;


@Controller
public class PaperController {
@Autowired
private SubjectService subjectService;

	
	@RequestMapping("Admin/openAddPaper")
	public ModelAndView  openPaper(@ModelAttribute("command")Paper p,Model model)
{
		
		
		List<Subject> sublist=subjectService.getAllSubject();
		System.out.println("add paper............."+sublist);
		model.addAttribute("sublist",sublist);
return new ModelAndView("paper/addPaper","command",new Paper());
}
	
}
