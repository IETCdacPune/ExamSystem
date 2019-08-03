package com.ietpune.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Admin")
public class AdminController {
	@RequestMapping("/")
	public String forAdminDashboard() {
		return "admin/adminDashboard";
	}
	@RequestMapping("/listOfStudent")
	public String forListOfStudent() {
		return "admin/listOfStudet";
	}

}
