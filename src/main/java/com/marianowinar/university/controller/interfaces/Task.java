package com.marianowinar.university.controller.interfaces;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PathVariable;

public interface Task<T> {	
	public String getResponse(ModelMap mp);
	public String getControlPanel(Model model, ModelMap mp);
	public String getRegister(Model model);	
	public String getUpdate(@PathVariable("id") Long id, Model model, ModelMap mp);
	public String getIdDelete(@PathVariable("id") Long id, Model model, ModelMap mp);
	public String getIdAddMaterial(@PathVariable("id") Long id, Model model, ModelMap mp);
	public String getIdDeleteMaterial(@PathVariable("id") Long id);
	public String getListProfMat(@PathVariable("id") Long id, Model model, ModelMap mp);
		
	public String postRegister(T entity, BindingResult result);
	public String postChangeProfile(@ModelAttribute T entity, BindingResult result);	
}
