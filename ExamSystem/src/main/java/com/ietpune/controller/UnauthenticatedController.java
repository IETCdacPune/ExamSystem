package com.ietpune.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UnauthenticatedController {
	@RequestMapping("/signin")
	public String forLogin() {
		return "signin";
	}
	
	@RequestMapping("/signup")
	public String forRegistration() {
		return "signup";
	}

}
