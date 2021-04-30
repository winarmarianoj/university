package com.marianowinar.university.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.marianowinar.university.repository.AccountRepository;
import com.marianowinar.university.repository.PersonRepository;
import com.marianowinar.university.service.entity.Account;
import com.marianowinar.university.service.entity.Person;


@Controller
public class AppController {
	
	@Autowired
	PersonRepository perRepo;
	
	@Autowired
	AccountRepository userRepo;

	@GetMapping({"/", "/login"})
	public String index() {
		return "index";
	}
	
	@GetMapping("/menu")
	public String hello(Model model, ModelMap mp) {
		Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();	
	    Account acc = userRepo.findByUsername(userDetail.getUsername()).orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));
	    Person person = perRepo.findByAccount(acc);
	    
		model.addAttribute("person", new Person());
		
		List<Person> list = new ArrayList<>();
		list.add(person);
		mp.put("persons", list);		
		return "menu";
	}
	

	

}
