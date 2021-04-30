package com.marianowinar.university.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class AccountController {

	@GetMapping("/profile")
	public String user() {
		return "/student/user";
	}
	
	@GetMapping("/prueba")
	public String prueba() {
		return "/student/prueba";
	}
}
