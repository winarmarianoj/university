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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.marianowinar.university.repository.AccountRepository;
import com.marianowinar.university.repository.PersonRepository;
import com.marianowinar.university.service.application.AccountService;
import com.marianowinar.university.service.application.AppService;
import com.marianowinar.university.service.application.PersonService;
import com.marianowinar.university.service.entity.Account;
import com.marianowinar.university.service.entity.Person;
import com.marianowinar.university.service.entity.source.Register;
import com.marianowinar.university.service.util.UserConnectedService;

@Controller
public class AppController {	
	
	@Autowired
	AppService appServ;
	
	@Autowired
	AccountService accServ;
	
	@Autowired
	PersonService perServ;
	
	@Autowired
	UserConnectedService userConnected;
	
	private List<Person> list;
	private List<String> messageList;
	private String messages;

	@GetMapping({"/", "/login"})
	public String index() {
		return "index";
	}
	
	@GetMapping("/menu")
	public String hello(Model model, ModelMap mp) {		
	    Person person = userConnected.personConected();	    
		model.addAttribute("person", new Person());		
		List<Person> list = new ArrayList<>();
		list.add(person);
		mp.put("persons", list);		
		return "menu";
	}
	
	@GetMapping("/forgot")
	public String user(Model model) {
		model.addAttribute("user", new Account());
		return "forgot";
	}
	
	@GetMapping("/registers")
	public String admin(Model model) {
		model.addAttribute("register", new Register());
		return "register";
	}
	
	@GetMapping("/resultRegistered")
	public String messageResult(ModelMap mp) {
		this.messageList = new ArrayList<>();
		messageList.add(this.messages);
		mp.put("messagess", messageList);
		return "message";
	}
	
	@PostMapping("/registrate")
	public String registered(@ModelAttribute Register entity, BindingResult result) {		
		String destiny = "";
		
		if(result.hasErrors()) {
			destiny = "redirect:/registers";
		}else {
			this.messages = appServ.registered(entity);
			destiny = "redirect:/resultRegistered";
		}
		return destiny;
	}
	

}
