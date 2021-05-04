package com.marianowinar.university.service.factory;

import com.marianowinar.university.service.entity.Material;
import com.marianowinar.university.service.entity.Person;
import com.marianowinar.university.service.entity.Professor;
import com.marianowinar.university.service.entity.source.Quota;
import com.marianowinar.university.service.entity.source.Register;
import com.marianowinar.university.service.enums.RoleName;

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
		
		if(entity.getType().equals(String.valueOf(RoleName.ROLE_ADMIN))) {
			per.setType("Admin");
		}else {
			per.setType("Student");
		}
		
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

	public Quota createQuota(Material ele) {
		Quota quota = new Quota();
		quota.setCapacity(ele.getCapacity());
		quota.setDetail(ele.getDetail());
		quota.setHour(ele.getHour());
		quota.setMaterialId(ele.getMaterialId());
		quota.setName(ele.getName());
		quota.setSubscribed(ele.getSubscribed());
		return quota;
	}

}