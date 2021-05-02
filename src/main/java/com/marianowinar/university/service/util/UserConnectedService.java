package com.marianowinar.university.service.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.marianowinar.university.service.application.AccountService;
import com.marianowinar.university.service.application.PersonService;
import com.marianowinar.university.service.entity.Account;
import com.marianowinar.university.service.entity.Person;

@Service
public class UserConnectedService {
	
	@Autowired
	PersonService perServ;
	
	@Autowired
	AccountService accServ;
	
	UserDetails userDetails;
	
	public String userConected() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String name = loggedInUser.getName(); 
		return name;
	}
	
	public Person personConected() {
		Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		Account account = accServ.findByUser(userDetails.getUsername());
		
		return perServ.findByPerson(account); 
	}
}
