package com.marianowinar.university.service.factory;


import com.marianowinar.university.service.entity.Person;
import com.marianowinar.university.service.entity.Register;

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
	

	/*
	public ShareMaterial createShare(Material ele) {
		ShareMaterial share = new ShareMaterial();
		share.setCapacity(ele.getCapacity());
		share.setDetail(ele.getDetail());
		share.setHour(ele.getHour());
		share.setName(ele.getName());
		share.setShare(0);
		share.setSubscribed(ele.getSubscribed());
		return share;
	}
	*/

}