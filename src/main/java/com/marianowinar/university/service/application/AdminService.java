package com.marianowinar.university.service.application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marianowinar.university.service.entity.Material;
import com.marianowinar.university.service.entity.Person;
import com.marianowinar.university.service.entity.source.Quota;
import com.marianowinar.university.service.entity.source.Register;
import com.marianowinar.university.service.factory.FactoryEntities;

@Service
public class AdminService {
	
	@Autowired
	PersonService perServ;
	
	@Autowired
	MaterialService matServ;
	
	@Autowired
	AccountService accServ;
	
	private FactoryEntities factory;
	
	public AdminService() {
		this.factory = FactoryEntities.getInstance();
	}

	public List<Person> studentOrdenar(){
		List<Person> sutdentList = new ArrayList<>();
		for(Person ele : perServ.viewAll()) {
			if(ele.getType().equals("Student")) {
				sutdentList.add(ele);
			}
		}		
		Collections.sort(sutdentList, new Comparator<Person>() {
			   public int compare(Person obj1, Person obj2) {
				   return obj1.getSurname().compareTo(obj2.getSurname());
			   }
			});
		return sutdentList;
	}
	
	public List<Quota> matOrdenarQuota() {
		List<Quota> quotaList = new ArrayList<>(); 
		for(Material ele : matServ.viewAll()) {
			Quota quota = factory.createQuota(ele);
			
			quota.setQuota(Integer.parseInt(ele.getCapacity()) - Integer.parseInt(ele.getSubscribed()));
			quotaList.add(quota);
		}
		Collections.sort(quotaList, new Comparator<Quota>() {
			   public int compare(Quota obj1, Quota obj2) {
				   return obj1.getName().compareTo(obj2.getName());
			   }
			});
		return quotaList;
	}

	public String updateAdmin(Register entity) {
		String message = accServ.updateAccount(entity);
		message = message + perServ.updatePerson(entity);
		return message;
	}
	
}
