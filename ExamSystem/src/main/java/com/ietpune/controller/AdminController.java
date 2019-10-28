package com.ietpune.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Admin")
public class AdminController {
	@GetMapping("")
	public String forAdminDashboard() {
		return "admin/adminDashboard";
	}
	@GetMapping("/listOfStudent")
	public String forListOfStudent() {
		return "admin/listOfStudent";
	}
	
}
