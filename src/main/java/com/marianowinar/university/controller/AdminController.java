package com.marianowinar.university.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping("/profile")
	public String admin() {
		return "/admins/admin";
	}
	
	@GetMapping("/prueba")
	public String prueba() {
		return "/admins/prueba";
	}

}
