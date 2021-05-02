package com.marianowinar.university.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marianowinar.university.controller.interfaces.Controllers;
import com.marianowinar.university.service.application.AccountService;
import com.marianowinar.university.service.application.AdminService;
import com.marianowinar.university.service.application.AppService;
import com.marianowinar.university.service.application.PersonService;
import com.marianowinar.university.service.entity.Account;
import com.marianowinar.university.service.entity.Person;
import com.marianowinar.university.service.entity.source.Register;
import com.marianowinar.university.service.util.UserConnectedService;

@Controller
@RequestMapping("/admin")
public class AdminController implements Controllers<Person>{
		
	@Autowired
	AdminService admServ;
	
	@Autowired
	AppService appServ;
	
	@Autowired
	PersonService perServ;	
	
	@Autowired
	AccountService accServ;
	
	@Autowired
	UserConnectedService userConnected;
	
	private String message;
	
	@Override
	@GetMapping("/profile")
	public String getProfile(Model model, ModelMap mp) throws ServletException, IOException {
		Person person = userConnected.personConected();	    
		model.addAttribute("person", new Person());		
		List<Person> list = new ArrayList<>();
		list.add(person);
		mp.put("persons", list);	
		return "/admins/profileAdmin";
	}
	
	@Override
	@GetMapping("/response")
	public String getResponse(ModelMap mp) {
		List<String> list = new ArrayList<>();
		list.add(this.message);
		mp.put("messages", list);
        return "/admins/responseAdmin";
	}
	
	@Override
	@GetMapping("/update")
	public String getUpdate(Model model, ModelMap mp) {
		Person person = userConnected.personConected();	
		List<Person> list = new ArrayList<>();
		list.add(person);
		mp.put("persons", list);		
		model.addAttribute("register", new Register());
        return "/admins/updateAdmin";
    }
	
	@Override
	@GetMapping("/delete")
	public String getDelete(Model model){
		//model.addAttribute("takeid", new Takeid());
        return "/admins/deletesAdmin";
    }
	
	@Override
	@GetMapping("/deleteConfirm")
	public String getDeleteConfirm() {
		String destiny = "";
		Person person = userConnected.personConected();
		if(perServ.delete(person.getId())) {
			destiny = "index";
		}else {
			this.message = "El Perfil y Usuario no han podido ser eliminados";
			destiny = "/admin/response";
		}		
		return destiny;
	}
	
	@GetMapping("/ListStudent")
	public String getListStudent(ModelMap mp) {
		mp.put("persons", admServ.studentOrdenar());
		return "/admins/ListOrderStudent";
	}
	
	@GetMapping("/quotas")
	public String getShare(ModelMap mp) {
		mp.put("quotas", admServ.matOrdenarQuota());
		return "/admins/quotaMaterial";
	}	

	@Override
	public String getLogout(Model model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String postProfile(Person entity, BindingResult result, ModelMap mp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@PostMapping(value = "/changeProfile")
	public String postChangeProfile(@ModelAttribute Register entity, BindingResult result, ModelMap mp) {
		String destiny = "";
		if(result.hasErrors()) {
			destiny= "redirect:/admin/update";
		}else{
			this.message = appServ.registered(entity);
			destiny= "redirect:/admin/response";	
		}		
		return destiny;
	}

	@Override	
	public String postDeleteProfile(Person entity, BindingResult result) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String postLogoutProfile(Person entity, BindingResult result) {
		// TODO Auto-generated method stub
		return null;
	}

}
