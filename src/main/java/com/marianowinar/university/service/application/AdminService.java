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

@Service
public class AdminService {
	
	@Autowired
	PersonService perServ;
	
	@Autowired
	MaterialService matServ;
	
	private List<Person> sutdentList;
	private List<Quota> quotaList;
	
	public AdminService() {
		this.sutdentList = new ArrayList<>();
		this.quotaList = new ArrayList<>();
	}

	public List<Person> studentOrdenar(){
		for(Person ele : perServ.viewAll()) {
			if(ele.getType().equals("STUDENT")) {
				this.sutdentList.add(ele);
			}
		}		
		Collections.sort(this.sutdentList, new Comparator<Person>() {
			   public int compare(Person obj1, Person obj2) {
				   return obj1.getSurname().compareTo(obj2.getSurname());
			   }
			});
		return this.sutdentList;
	}
	
	public List<Quota> matOrdenarQuota() {
		for(Material ele : matServ.viewAll()) {
			Quota quota = (Quota) ele;
			quota.setQuota(Integer.parseInt(ele.getCapacity()) - Integer.parseInt(ele.getSubscribed()));
			this.quotaList.add(quota);
		}
		Collections.sort(this.quotaList, new Comparator<Quota>() {
			   public int compare(Quota obj1, Quota obj2) {
				   return obj1.getName().compareTo(obj2.getName());
			   }
			});
		return this.quotaList;
	}
}
