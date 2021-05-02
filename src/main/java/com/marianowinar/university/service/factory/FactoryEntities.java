package com.marianowinar.university.service.factory;

import com.marianowinar.university.service.entity.Person;
import com.marianowinar.university.service.entity.Professor;
import com.marianowinar.university.service.entity.source.Register;

public class FactoryEntities {
	
	private static FactoryEntities fact;
	
	private FactoryEntities() {}
	
	public static FactoryEntities getInstance() {
		if(fact == null) fact = new FactoryEntities();
		
		return fact;
	}
	
	public Person createPerson(Register entity) {
		Person per = new Person();
		per.setName(entity.getName());
		per.setSurname(entity.getSurname());
		per.setPhone(entity.getPhone());
		per.setEmail(entity.getEmail());
		per.setType(entity.getType());
		return per;
	}

	public Professor createProfessor(Professor entity) {
		Professor prof = new Professor();
		prof.setActive(true);
		prof.setEmail(entity.getEmail());
		prof.setName(entity.getName());
		prof.setPhone(entity.getPhone());
		prof.setSurname(entity.getSurname());
		prof.setType("Professor");
		return prof;
	}

}