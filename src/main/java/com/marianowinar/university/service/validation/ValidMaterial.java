package com.marianowinar.university.service.validation;

import java.util.regex.Pattern;

import com.marianowinar.university.service.entity.Material;
import com.marianowinar.university.service.exception.material.InvalidCapacityMaterialException;
import com.marianowinar.university.service.exception.material.InvalidDetailException;
import com.marianowinar.university.service.exception.material.InvalidHourException;
import com.marianowinar.university.service.exception.material.InvalidNameMaterialException;
import com.marianowinar.university.service.exception.material.InvalidSubscribedException;
import com.marianowinar.university.service.exception.material.MaterialException;
import com.marianowinar.university.service.exception.material.NullMaterialException;
import com.marianowinar.university.service.exception.person.InvalidLastNameException;

public class ValidMaterial extends Validator{
	
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
		
		if(validateString(name)){
			throw new InvalidNameMaterialException("EL nombre no es válido");
        }
	}

	public void validDetail(String detail) throws InvalidDetailException {
		if(detail == null)
			throw new InvalidDetailException("El Detalle es nulo");
		
		if(validateString(detail)){
			throw new InvalidDetailException("El Detalle no es válido");
        }
	}

	public void validSubscribed(String subscribed) throws InvalidSubscribedException {
		if(subscribed == null)
			throw new InvalidSubscribedException("Subscriptos es nulo");
		
		int subs = Integer.parseInt(subscribed);
		if(subs < SUBSCRIPTION_MENOR && subs > SUBSCRIPTION_MAYOR)
			throw new InvalidSubscribedException("Subscriptos es incorrecta");
	}

	public void validCapacity(String capacity) throws InvalidCapacityMaterialException {
		if(capacity == null)
			throw new InvalidCapacityMaterialException("la capacidad es nula");
		
		int capa = Integer.parseInt(capacity);
		if(capa < CAPACITY_MENOR && capa > CAPACITY_MAYOR)
			throw new InvalidCapacityMaterialException("la capacidad es incorrecta");		
	}

	public void validHour(String hour) throws InvalidHourException {
		if(hour == null)
			throw new InvalidHourException("La hora es nula");
		
		int hours = Integer.parseInt(hour);
		if(hours < HOUR_MENOR && hours > HOUR_MAYOR) 
			throw new InvalidHourException("La hora es incorrecta");		
	}		
	
	private boolean validateString(String string) {
        boolean isValid = false;

        if (!Pattern.matches(REGEX_NAMES, string))
            isValid = true;

        return isValid;
    }	
	
}
