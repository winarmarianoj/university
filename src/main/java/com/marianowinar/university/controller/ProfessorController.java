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
import com.marianowinar.university.service.application.ProfessorService;
import com.marianowinar.university.service.entity.Professor;
import com.marianowinar.university.service.entity.source.AddMaterial;

@Controller
@RequestMapping(value = "/admin/professor")
public class ProfessorController implements Task<Professor>{
	
	@Autowired
	ProfessorService profServ;
	
	@Autowired
	MaterialService matServ;
	
	private String message;
	private Long idProf;
	
	public ProfessorController() {	}

	@Override
	@GetMapping("/response")
	public String getResponse(ModelMap mp) {
		List<String> messList = new ArrayList<>();
		messList.add(this.message);
		mp.put("messages", messList);
        return "/professor/respProfessor";
	}

	@Override
	@GetMapping("/controlPanel")
	public String getControlPanel(Model model, ModelMap mp) {
		model.addAttribute("professor", new Professor());	
		mp.put("professors", profServ.viewAll());
		return "/professor/professorControlPanel";
	}

	
	@Override
	@GetMapping("/createProfessor")
	public String getRegister(Model model) {
		model.addAttribute("professor", new Professor());
		return "/professor/registerProfessor";		
	}
	
	@Override
	@GetMapping("/update/{id}")
	public String getUpdate(@PathVariable("id") Long id, Model model, ModelMap mp) {
		Professor professor = profServ.searchingProfessor(id);
		List<Professor> list = new ArrayList<>();
		list.add(professor);
		mp.put("professors", list);
		model.addAttribute("professor", new Professor());
		return "/professor/updateProfessor";
	}
	
	@Override
	@GetMapping("/delete/{id}")
	public String getIdDelete(@PathVariable("id") Long id, Model model, ModelMap mp) {
		this.message = profServ.deleteProfessor(id);
		return "redirect:/admin/professor/response";
	}
		
	/*
	 * Add Material of Professor
	 */
	@Override
	@GetMapping("/addMaterial/{id}") 
	public String getIdAddMaterial(@PathVariable("id") Long id, Model model, ModelMap mp) {
		model.addAttribute("addMaterial", new AddMaterial());
		List<AddMaterial> addMatList = profServ.createAddMaterial(id);		
		mp.put("addMaterials", addMatList);
		this.idProf = id;
		return "/professor/professorAddMaterial";
	}
	
	@GetMapping("/addMaterialNew/{id}") 
	public String getIdAddMaterial(@PathVariable("id") Long id) {
		this.message = profServ.addMaterial(this.idProf, id);
		return "redirect:/admin/professor/response";
	}
	
	/*
	 * Delete Material of Professor
	 */
	@Override
	@GetMapping("/deleteMaterial/{id}")
	public String getIdDeleteMaterial(@PathVariable("id") Long id) {
		this.message = profServ.deleteMaterial(this.idProf, id);
		return "redirect:/admin/professor/response";
	}
	
	/*
	 * List Material of Professor
	 */
	@Override
	@GetMapping("/listMaterials/{id}")
	public String getListProfMat(@PathVariable("id") Long id, Model model, ModelMap mp) {
		model.addAttribute("professor", new Professor());
		Professor prof = profServ.searchingProfessor(id);
		List<Professor> profList = new ArrayList<>();		
		profList.add(prof);
		mp.put("professors", profList);
		mp.put("materials", prof.getListMaterial());
		return "/professor/profListMat";
	}

	/*
	 * CREATE PROFESSOR
	 */
	@Override
	@PostMapping(value = "/registerProfessor")
	public String postRegister(@ModelAttribute Professor entity, BindingResult result) {
		String destiny = "";		
		if(result.hasErrors()) {
			destiny = "redirect:/admin/professor/controlPanel";
		}else {
			this.message = profServ.createProfessor(entity);
			destiny = "redirect:/admin/professor/response";
		}
		return destiny;
	}

	/*
	 * CHANGE PROFESSOR
	 */
	@Override
	@PostMapping(value = "/changeProfessor")
	public String postChangeProfile(@ModelAttribute Professor entity, BindingResult result) {
		String destiny = "";
		if(result.hasErrors()){		        
	        destiny = "redirect:/admin/professor/controlPanel";
	    }else {
	    	this.message = profServ.update(entity);
	    	destiny = "redirect:/admin/professor/response";
	    }
	    return destiny;
	}	

}
