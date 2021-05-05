package com.marianowinar.university.service.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marianowinar.university.repository.PersonRepository;
import com.marianowinar.university.service.entity.Person;
import com.marianowinar.university.service.entity.source.Register;
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
	public String create(Person entity) {
		String result = "";		
		try {
			valPer.validPerson(entity);
			perRepo.save(entity);
			result = "La Persona fue Salvada y Agregada exitosamente a BD!";
		}catch(PersonException e) {
			errors.logError(e.getError());
			result = e.getError() + " // " + "La Persona No se pudo modificar o los datos son incorrectos.";
		}
		return result;
	}

	@Override
	public String update(Person entity) {
		return create(entity);
	}

	@Override
	public boolean delete(Long id) {
		perRepo.deleteById(id);
		if(existsById(id)) {
			return false;
		}
		return true;
	}

	@Override
	public List<Person> viewAll() {
		return perRepo.findAll();
	}

	@Override
	public boolean existsById(Long id) {
		return perRepo.existsById(id);
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
		return perRepo.findByAccount(us);
	}

	/**
	 * Busca si existe la cuenta, sino la crea
	 * @param entity Clase con datos de Registro nuevo
	 * @param us Account nuevo
	 * @return true o false
	 */
	public Person createAdminUser(Register entity) {		
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

	/**
	 * Modificamos datos de la Persona
	 * @param entity Objeto con los datos desde la web
	 * @param us Cuenta de Usuario
	 * @return Person modificado
	 */
	public String updatePerson(Register entity) {
		Account acc = accServ.findByUser(entity.getDni());
		Person per = findByPerson(acc);
		per.setEmail(entity.getEmail());
		per.setName(entity.getName());
		per.setPhone(entity.getPhone());
		per.setSurname(entity.getSurname());
		return update(per);
	}

}
