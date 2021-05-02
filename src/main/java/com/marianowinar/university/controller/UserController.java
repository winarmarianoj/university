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
import org.springframework.web.bind.annotation.RequestMapping;

import com.marianowinar.university.controller.interfaces.Task;
import com.marianowinar.university.service.application.UserService;
import com.marianowinar.university.service.entity.Person;
import com.marianowinar.university.service.entity.source.Register;
import com.marianowinar.university.service.util.UserConnectedService;

@Controller
@RequestMapping(value = "/user/student")
public class UserController implements Task<Person>{
	
	@Autowired
	UserService userServ;
	
	@Autowired
	UserConnectedService userConnected;
	
	private String message;

	@Override
	@GetMapping("/response")
	public String getResponse(ModelMap mp) {
		List<String> list = new ArrayList<>();
		list.add(this.message);
		mp.put("messages", list);
        return "/student/responseStudent";
	}

	@Override
	@GetMapping("/profile")
	public String getControlPanel(Model model, ModelMap mp) {
		Person person = userConnected.personConected();	    
		model.addAttribute("person", new Person());		
		List<Person> list = new ArrayList<>();
		list.add(person);
		mp.put("persons", list);	
		return "/student/profileStudent";
	}

	@Override
	public String getRegister(Model model) {
		return null;
	}

	@Override
	@GetMapping("/update")
	public String getUpdate(Long id, Model model, ModelMap mp) {
		Person person = userConnected.personConected();	
		List<Person> list = new ArrayList<>();
		list.add(person);
		mp.put("persons", list);		
		model.addAttribute("register", new Register());
        return "/student/updateAdmin";
	}

	@Override
	public String getIdDelete(Long id, Model model, ModelMap mp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIdAddMaterial(Long id, Model model, ModelMap mp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIdDeleteMaterial(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getListProfMat(Long id, Model model, ModelMap mp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String postRegister(Person entity, BindingResult result) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String postChangeProfile(Person entity, BindingResult result) {
		return null;		
	}
	
	
	@PostMapping(value = "/update")
	public String postUpdate(@ModelAttribute Register entity, BindingResult result) {
		String destiny = "";
		if(result.hasErrors()) {
			destiny= "redirect:/user/student/update";
		}else{
			this.message = userServ.updateStudent(entity);
			destiny= "redirect:/admin/response";	
		}		
		return destiny;		
	}

}
