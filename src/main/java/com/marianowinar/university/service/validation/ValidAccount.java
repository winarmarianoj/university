package com.marianowinar.university.service.validation;

import com.marianowinar.university.service.entity.Account;
import com.marianowinar.university.service.exception.account.AccountException;
import com.marianowinar.university.service.exception.account.InvalidDniException;
import com.marianowinar.university.service.exception.account.InvalidPasswordAccountException;
import com.marianowinar.university.service.exception.account.NullAccountException;

public class ValidAccount {

	private static ValidAccount validAccount;
	
	private ValidAccount() {}
	
	public static ValidAccount getInstance() {
		if(validAccount == null) validAccount = new ValidAccount();
		
		return validAccount;
	}
	
	public void validCreateAccount(Account aco) throws AccountException{
		if(aco == null)
			throw new NullAccountException();
		
		validateDni(aco.getUsername());
		validateLegajo(aco.getPassword());
		validateLeg(aco.getLegajo());
	}

	public void validateLegajo(String legajo) throws InvalidPasswordAccountException {
		if(legajo == null)
			throw new InvalidPasswordAccountException("Legajo es nulo");
	}

	public void validateDni(String dni) throws InvalidDniException {
		if(dni == null)
			throw new InvalidDniException("DNI es nulo");
	}	
	
	public void validateLeg(String legajo) throws InvalidDniException {
		if(legajo == null)
			throw new InvalidDniException("El Legajo es nulo");
	}
	
}
