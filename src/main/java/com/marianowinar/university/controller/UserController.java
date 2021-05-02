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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marianowinar.university.controller.interfaces.Task;
import com.marianowinar.university.service.application.MaterialService;
import com.marianowinar.university.service.application.UserService;
import com.marianowinar.university.service.entity.Material;
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
	
	@Autowired
	MaterialService matServ;
	
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
		// No se utiliza.
		return null;
	}

	@Override	
	public String getUpdate(Long id, Model model, ModelMap mp) {		
        return null;
	}
	
	@GetMapping("/update")
	public String getUpdateStudent(Model model, ModelMap mp) {
		Person person = userConnected.personConected();	
		List<Person> list = new ArrayList<>();
		list.add(person);
		mp.put("persons", list);		
		model.addAttribute("register", new Register());
        return "/student/updateStudent";
	}

	@Override	
	public String getIdDelete(Long id, Model model, ModelMap mp) {
		return null;
	}
		
	@GetMapping("/delete")
	public String getDeleteStudent(Long id, Model model, ModelMap mp) {
		return "/student/deletesUser";
	}
	
	@GetMapping("/deleteConfirm")
	public String getDeleteConfirm() {
		String destiny = "";
		Person person = userConnected.personConected();
		if(userServ.delete(person.getId())) {
			destiny = "redirect:/";
		}else {
			this.message = "El Perfil y Usuario no han podido ser eliminados";
			destiny = "redirect:/user/student/response";
		}		
		return destiny;
	}
	
	@GetMapping("/inscription")
	public String getInscription(ModelMap mp) {
		mp.put("materials", matServ.viewAll());
        return "/student/inscriptionStudent";
	}

	@Override
	@GetMapping("/addMaterial/{id}")   
	public String getIdAddMaterial(@PathVariable("id") Long id, Model model, ModelMap mp) {
		Person person = userConnected.personConected();	
		this.message = userServ.addMaterial(person,id);
		return "redirect:/user/student/response";
	}

	@Override
	@GetMapping("/deleteMaterial/{id}")
	public String getIdDeleteMaterial(@PathVariable("id") Long id) {
		Person person = userConnected.personConected();
		this.message = userServ.deleteMaterial(person,id);
		return "redirect:/user/student/response";
	}

	@Override
	@GetMapping("/listStudents/{id}")
	public String getListProfMat(@PathVariable("id") Long id, Model model, ModelMap mp) {
		Material mat = matServ.searchingMaterial(id);
		List<Person> list = userServ.listStudentForMaterial(id,mat);
		List<Material> matList = new ArrayList<>();
		matList.add(mat);
		mp.put("students", list);
		mp.put("materials", matList);
		return "/student/listMaterialStudentInscripted";
	}
	
	@GetMapping("/subscribed")
	public String getListMaterialsSubscribed(Model model, ModelMap mp) {
		Person person = userConnected.personConected();
		List<Material> matList = person.getListMaterial();
		List<Person> stuList = new ArrayList<>();
		stuList.add(person);
		mp.put("students", stuList);
		mp.put("materials", matList);
		return "/student/listMaterialsSubscribed";		
	}

	@Override
	public String postRegister(Person entity, BindingResult result) {
		// No se utiliza.
		return null;
	}

	@Override
	public String postChangeProfile(Person entity, BindingResult result) {
		// No se utiliza.
		return null;		
	}
	
	
	@PostMapping(value = "/update")
	public String postUpdate(@ModelAttribute Register entity, BindingResult result) {
		String destiny = "";
		if(result.hasErrors()) {
			destiny= "redirect:/user/student/update";
		}else{
			this.message = userServ.updateStudent(entity);
			destiny= "redirect:/user/student/response";	
		}		
		return destiny;		
	}

}
