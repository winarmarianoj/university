package com.marianowinar.university.service.validation;

import com.marianowinar.university.service.entity.Material;
import com.marianowinar.university.service.exception.material.InvalidCapacityMaterialException;
import com.marianowinar.university.service.exception.material.InvalidDetailException;
import com.marianowinar.university.service.exception.material.InvalidHourException;
import com.marianowinar.university.service.exception.material.InvalidNameMaterialException;
import com.marianowinar.university.service.exception.material.InvalidSubscribedException;
import com.marianowinar.university.service.exception.material.MaterialException;
import com.marianowinar.university.service.exception.material.NullMaterialException;

public class ValidMaterial {
	
	private static ValidMaterial validMaterial;
	
	private ValidMaterial() {}
	
	public static ValidMaterial getInstance() {
		if(validMaterial == null) validMaterial = new ValidMaterial();
		
		return validMaterial;
	}	
	
	public void validNameMaterial(Material mat) throws MaterialException {
		if(mat == null)
			throw new NullMaterialException();
		
		validName(mat.getName());
		validHour(mat.getHour());
		validCapacity(mat.getCapacity());
		validSubscribed(mat.getSubscribed());
		validDetail(mat.getDetail());
		
	}

	public void validName(String name) throws InvalidNameMaterialException {
		if(name == null)
			throw new InvalidNameMaterialException("EL nombre es nulo");
	}

	public void validDetail(String detail) throws InvalidDetailException {
		if(detail == null)
			throw new InvalidDetailException("El Detalle es nulo");
	}

	public void validSubscribed(String subscribed) throws InvalidSubscribedException {
		if(subscribed == null)
			throw new InvalidSubscribedException("Subscriptos es nulo");
		
		int subs = Integer.parseInt(subscribed);
		if(subs < 0 && subs > 100)
			throw new InvalidSubscribedException("Subscriptos es incorrecta");
	}

	public void validCapacity(String capacity) throws InvalidCapacityMaterialException {
		if(capacity == null)
			throw new InvalidCapacityMaterialException("la capacidad es nula");
		
		int capa = Integer.parseInt(capacity);
		if(capa < 0 && capa > 100)
			throw new InvalidCapacityMaterialException("la capacidad es incorrecta");		
	}

	public void validHour(String hour) throws InvalidHourException {
		if(hour == null)
			throw new InvalidHourException("La hora es nula");
		
		int hours = Integer.parseInt(hour);
		if(hours < 0 && hours > 24) 
			throw new InvalidHourException("La hora es incorrecta");		
	}	
	
	
}
