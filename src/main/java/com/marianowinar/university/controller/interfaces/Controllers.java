package com.marianowinar.university.controller.interfaces;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public interface Controllers<T>{
	
	public String getProfile(Model model, ModelMap mp) throws ServletException, IOException;
	public String getUpdate(Model model, ModelMap mp) throws ServletException, IOException;
	public String getDelete(Model model);
	public String getLogout(Model model);
	
	public String postProfile(@ModelAttribute T entity, BindingResult result, ModelMap mp);
	public String postChangeProfile(@ModelAttribute T entity, BindingResult result, ModelMap mp);
	public String postDeleteProfile(@ModelAttribute T entity, BindingResult result);
	public String postLogoutProfile(@ModelAttribute T entity, BindingResult result);
	
}