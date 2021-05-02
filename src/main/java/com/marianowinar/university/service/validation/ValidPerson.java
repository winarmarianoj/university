package com.marianowinar.university.service.validation;

import java.util.regex.Pattern;

import com.marianowinar.university.service.entity.Person;
import com.marianowinar.university.service.exception.person.InvalidLastNameException;
import com.marianowinar.university.service.exception.person.InvalidMailException;
import com.marianowinar.university.service.exception.person.InvalidNameException;
import com.marianowinar.university.service.exception.person.InvalidPhoneException;
import com.marianowinar.university.service.exception.person.InvalidTypePersonException;
import com.marianowinar.university.service.exception.person.NullPersonException;
import com.marianowinar.university.service.exception.person.PersonException;

public class ValidPerson {
	
	private static ValidPerson types;
	
	private ValidPerson() {}
	
	public static ValidPerson getInstance() {
		if(types == null) types = new ValidPerson();
		
		return types;
	}
	
	public void validPerson(Person per) throws PersonException{
		if(per == null)
			throw new NullPersonException();
		
		validateName(per.getName());
		validateLastName(per.getSurname());
		validateEmail(per.getEmail());
		validatePhone(per.getPhone());
		validateType(per.getType());
	}
	public void validateType(String type) throws InvalidTypePersonException {
		if(type == null)
			throw new InvalidTypePersonException("Tipo de Usuario es nulo");
		
		if(!type.equals("Admin") && !type.equals("Student") && !type.equals("Professor"))
            throw new InvalidTypePersonException("type");
	}
	
	public void validatePhone(String phone) throws InvalidPhoneException {
		if(phone == null)
			throw new InvalidPhoneException("Phone es nulo");		
	}
	
	public void validateEmail(String email) throws InvalidMailException{		
        if (email == null) 
        	throw new InvalidMailException("Email es nulo");

        if (!Pattern.matches("^([a-zA-Z0-9-._ñ]+)@([a-zA-Z0-9-._ñ]+).([a-zA-Z]{2,5})$",email)) 
        	throw new InvalidMailException(email);
    }
	
	public void validateLastName(String lastname) throws InvalidLastNameException {
        if(lastname == null)
            throw new InvalidLastNameException("Apellido es nulo");
        
        if(validateString(lastname)){
            throw new InvalidLastNameException(lastname);
        }
    }

    public void validateName(String name) throws InvalidNameException {
        if(name == null)
            throw new InvalidNameException("Nombre es nulo");

        if(validateString(name))
            throw new InvalidNameException(name);
    }

    private boolean validateString(String string) {
        boolean isValid = false;

        if (!Pattern.matches("^([a-zA-ZñÑ])+$", string))
            isValid = true;

        return isValid;
    }
	
}
