package com.marianowinar.university.service.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marianowinar.university.repository.UserRepository;
import com.marianowinar.university.service.entity.Account;
import com.marianowinar.university.service.entity.Person;
import com.marianowinar.university.service.entity.source.Register;
import com.marianowinar.university.service.factory.FactoryEntities;
import com.marianowinar.university.service.interfaces.Services;
import com.marianowinar.university.service.logger.Errors;
import com.marianowinar.university.service.validation.ValidPerson;

@Service
public class UserService implements Services<Person>{
	
	@Autowired
	UserRepository userRepo;
	
	private ValidPerson valPer;
	private Errors errors;
	private FactoryEntities factory;
	
	public UserService() {
		this.valPer = ValidPerson.getInstance();
		this.errors = Errors.getInstance();
		this.factory = FactoryEntities.getInstance();
	}

	@Override
	public boolean create(Person entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String update(Person entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Person> viewAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existsByObject(Person entity) {
		// TODO Auto-generated method stub
		return false;
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

	public String updateStudent(Register entity) {
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
