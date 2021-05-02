package com.marianowinar.university.service.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.marianowinar.university.service.entity.Person;
import com.marianowinar.university.service.entity.source.Forgot;
import com.marianowinar.university.service.entity.source.Register;
import com.marianowinar.university.service.entity.Account;

@Service
public class AppService {
	
	@Autowired
	AccountService accServ;
	
	@Autowired
	PersonService perServ;	
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public AppService(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

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

	/**
	 * Cambio de Contraseña de un Usuario
	 * @param entity tipo de objeto Forgot con los datos que vienen de la web
	 * @return mensaje del resultado de la operación
	 */
	public String forgotNew(Forgot entity) {
		String message = "Password incorrecto o no son iguales";
		Account acc = accServ.takeUser(entity.getDni());
		if(entity.getPassword().equals(entity.getPassword2())) {
			String encodedPassword = bCryptPasswordEncoder.encode(entity.getPassword());		
			acc.setPassword(encodedPassword);
			message = accServ.update(acc);
		}
		return message;
	}

}
