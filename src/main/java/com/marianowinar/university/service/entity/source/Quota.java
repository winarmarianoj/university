package com.marianowinar.university.service.entity.source;

import java.util.List;

import com.marianowinar.university.service.entity.Material;
import com.marianowinar.university.service.entity.Person;

public class Quota extends Material{

	private int quota;

	public Quota() {}

	public Quota(Long materialId, String name, String hour, String capacity, List<Person> persons,
			String subscribed, String detail, int quota) {
		super(materialId, name, hour, capacity, persons, subscribed, detail);
		this.quota = quota;
	}

	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}
	
}
