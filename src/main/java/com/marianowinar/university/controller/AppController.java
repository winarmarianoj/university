package com.marianowinar.university.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.marianowinar.university.repository.RoleRepository;
import com.marianowinar.university.service.application.AccountService;
import com.marianowinar.university.service.application.AppService;
import com.marianowinar.university.service.application.PersonService;
import com.marianowinar.university.service.application.RoleService;
import com.marianowinar.university.service.entity.Person;
import com.marianowinar.university.service.entity.Role;
import com.marianowinar.university.service.entity.source.Forgot;
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
	
	@Autowired
	RoleService roleServ;
	
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
	public String forgot(Model model) {
		model.addAttribute("forgot", new Forgot());
		return "forgot";
	}
	
	@GetMapping("/registers")
	public String admin(Model model, ModelMap mp) {
		Role userRole = roleServ.takeRole("ROLE_USER");
		Role adminRole = roleServ.takeRole("ROLE_ADMIN");
		List<Role> roleList = new ArrayList<>();
		roleList.add(userRole);
		roleList.add(adminRole);
		mp.put("roles", roleList);
		model.addAttribute("register", new Register());
		return "register";
	}
	
	@GetMapping("/results")
	public String messageResult(ModelMap mp) {
		List<String> messageList = new ArrayList<>();
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
			destiny = "redirect:/results";
		}
		return destiny;
		
		//<input th:field="*{type}" type="text" class="form-control" id="type" placeholder="Admin / Student">
	}
	
	@PostMapping("/changeForgot")
	public String changePass(@ModelAttribute Forgot entity, BindingResult result) {
		String destiny = "";
		
		if(result.hasErrors()) {
			destiny = "redirect:/forgot";
		}else {
			this.messages = appServ.forgotNew(entity);
			destiny = "redirect:/results";
		}
		return destiny;
	}
	

}
