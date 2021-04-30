package com.marianowinar.university.service.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marianowinar.university.repository.PersonRepository;
import com.marianowinar.university.service.entity.Person;
import com.marianowinar.university.service.entity.Register;
import com.marianowinar.university.service.entity.Account;
import com.marianowinar.university.service.exception.person.PersonException;
import com.marianowinar.university.service.factory.FactoryEntities;
import com.marianowinar.university.service.interfaces.Services;
import com.marianowinar.university.service.logger.Errors;
import com.marianowinar.university.service.validation.ValidPerson;

@Service
@Transactional
public class PersonService implements Services<Person>{
	
	@Autowired 
	PersonRepository perRepo;
	
	@Autowired
	AccountService accServ;
	
	private ValidPerson valPer;
	private Errors errors;
	private FactoryEntities factory;
	
	public PersonService() {
		this.valPer = ValidPerson.getInstance();
		this.errors = Errors.getInstance();
		this.factory = FactoryEntities.getInstance();
	}
	
	@Override
	public boolean create(Person entity) {
		boolean res = false;
		
		try {
			valPer.validPerson(entity);
			perRepo.save(entity);
			res = true;
		}catch(PersonException e) {
			errors.logError(e.getError());
		}
		return res;
	}

	@Override
	public void update(Person entity) {
		perRepo.save(entity);
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Person> viewAll() {
		return perRepo.findAll();
	}
	
	@Override
	public Person take(Long id) {		
		return null;
	}

	@Override
	public Person getByName(String name) {
		return null;
	}

	@Override
	public boolean existsById(long id) {
		return perRepo.existsById(id);
	}

	@Override
	public boolean existsByObject(Person person) {
		return false;
	}
	
	/*
	 * METHODS AND FUNCTION
	 */

	/**
	 * Devuelve el objeto Person según Account Conectado
	 * @param us Account conectado
	 * @return Person
	 */
	public Person findByPerson(Account us) {
		Person person = new Person();
		for(Person ele : viewAll()) {
			if(ele.getAccount().getUsername().equals(us.getUsername())) {
				person = ele;
				break;
			}
		}
		return person;
	}

	/**
	 * Busca si existe la cuenta, sino la crea
	 * @param entity Clase con datos de Registro nuevo
	 * @param us Account nuevo
	 * @return true o false
	 */
	public Person createAdmin(Register entity) {		
		Person person = new Person();
		if(!searchPersonNew(entity)) {			
			person = factory.createPerson(entity);						
			create(person);			
		}		
		return person;
	}

	/**
	 * Busca si la persona a ingresar ya existe o no en BD
	 * @param entity Clase con datos de Registro nuevo
	 * @return true o false
	 */
	private boolean searchPersonNew(Register entity) {
		boolean res = false;
		for(Person ele : viewAll()) {
			if(ele.getName().equals(entity.getName()) && ele.getSurname().equals(entity.getSurname())) {
				res = true;
				break;
			}
		}
		return res;
	}

}
