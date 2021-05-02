package com.marianowinar.university.service.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marianowinar.university.service.entity.Person;
import com.marianowinar.university.service.entity.source.Register;
import com.marianowinar.university.service.entity.Account;

@Service
public class AppService {
	
	@Autowired
	AccountService accServ;
	
	@Autowired
	PersonService perServ;	

	/**
	 * Registramos Account y Person nuevos
	 * @param entity Objeto Register con los datos del nuevo Account, Role y Person
	 * @return el Mensaje para mostrar por pantalla del resultado
	 */
	public String registered(Register entity) {
		String message = "";
		Person person = perServ.createAdmin(entity); 
		Account us = accServ.createUser(entity); 		
		
		if(us != null) {
			person.setAccount(us);
			perServ.update(person);
			message = "La Cuenta se ha creado satisfactoriamente. Puede loguearse!!! Bienvenido al Sitio";
		}else {
			message = "Ha cargado datos erróneos, vuelva a intentarlo.";
		}
		return message;
	}

}
