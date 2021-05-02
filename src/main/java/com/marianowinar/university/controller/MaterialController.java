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
import com.marianowinar.university.service.entity.Material;

@Controller
@RequestMapping(value = "/admin/material")
public class MaterialController implements Task<Material>{
	
	@Autowired
	MaterialService matServ;
	
	private String message;
	
	public MaterialController() {}
		
	@Override
	@GetMapping("/responseMaterial")
	public String getResponse(ModelMap mp) {
		List<String> messList = new ArrayList<>();
		messList.add(this.message);
		mp.put("messages", messList);
        return "/material/respMaterial";
	}

	@Override
	@GetMapping("/controlPanel")
	public String getControlPanel(Model model, ModelMap mp) {
		model.addAttribute("material", new Material());
		mp.put("materials", matServ.viewAll());
		return "/material/materialControlPanel";
	}

	@Override
	@GetMapping("/createMaterial")
	public String getRegister(Model model) {
		model.addAttribute("material", new Material());
		return "/material/registerMaterial";
	}	
	
	@Override
	@GetMapping("/update/{id}")
	public String getUpdate(@PathVariable("id") Long id, Model model, ModelMap mp) {
		Material material = matServ.searchingMaterial(id);		
		List<Material> list = new ArrayList<>();
		list.add(material);
		mp.put("materials", list);
		model.addAttribute("material", new Material());
		return "/material/updateMaterial";
	}
	
	@Override
	@GetMapping("/delete/{id}")
	public String getIdDelete(@PathVariable("id") Long id, Model model, ModelMap mp) {		
		this.message = matServ.deleteMaterial(id);
		String destiny = "redirect:/admin/material/responseMaterial"; 
		return destiny;
	}
		
	
	@GetMapping("/takeNameListMaterialProfessor")
	public String getIdNameListProfMat(Model model, ModelMap mp) {
		model.addAttribute("material", new Material());
		mp.put("materials", matServ.viewAll());
		return "/material/takeNameListMaterialProfessor";
	}
	
	@Override
	public String getIdAddMaterial(Long id, Model model, ModelMap mp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIdDeleteMaterial(Model model, ModelMap mp) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@GetMapping("/matListProf/{id}")
	public String getListProfMat(@PathVariable("id") Long id, Model model, ModelMap mp) {
		Material material = matServ.searchingMaterial(id);
		mp.put("persons", material.getListPerson());
		List<Material> list = new ArrayList<>();
		list.add(material);		
		mp.put("materials", list);		
		return "/material/matListProf";
	}

	/**
	 * CREA MATERIAL NUEVO, objeto Material recibido de web
	 */
	@Override
	@PostMapping(value = "/registerMaterial")
	public String postRegister(@ModelAttribute Material entity, BindingResult result) {
        String destiny = "";		
		if(result.hasErrors()) {
			destiny= "redirect:/admin/material/createMaterial";
		}else {
			this.message = matServ.createMaterial(entity);
			destiny = "redirect:/admin/material/responseMaterial";
		}
		return destiny;
	}	

	/*
	 * CAMBIO DEL OBJETO MATERIA, objeto Material recibido de la web
	 */
	@Override
	@PostMapping(value = "/changeMaterial")
	public String postChangeProfile(@ModelAttribute Material entity, BindingResult result) {
		System.out.println("la entity que llego de material a controler es" + " " + entity.getId() + " " + entity.getName());
		String destiny = "";
		if(result.hasErrors()){		        
	        destiny = "redirect:/admin/material/controlPanel";
	    }else {
	    	this.message = matServ.update(entity);
	    	destiny = "redirect:/admin/material/responseMaterial";
	    }
	    return destiny;
	}



		
}