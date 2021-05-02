package com.marianowinar.university.controller.interfaces;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.marianowinar.university.service.entity.source.Register;

@Controller
public interface Controllers<T>{	
	public String getProfile(Model model, ModelMap mp) throws ServletException, IOException;
	public String getResponse(ModelMap mp);
	public String getUpdate(Model model, ModelMap mp) throws ServletException, IOException;
	public String getDelete(Model model);
	public String getDeleteConfirm();
	public String getListStudent(ModelMap mp);
	public String getShare(ModelMap mp);	
	public String postChangeProfile(@ModelAttribute Register entity, BindingResult result, ModelMap mp);	
}