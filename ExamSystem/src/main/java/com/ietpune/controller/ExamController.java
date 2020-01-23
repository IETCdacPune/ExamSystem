package com.ietpune.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ietpune.model.Paper;
import com.ietpune.service.PaperService;

@RestController
@RequestMapping("/paper")
public class ExamController {
	@Autowired PaperService paperService;
	Logger logger=Logger.getLogger(ExamController.class);
	@GetMapping("/{id}")
	public Paper getPaper(@PathVariable("id") Integer id) {
		logger.info("In exam logger id:"+id);
		return paperService.getPaper(id);
	}
}
