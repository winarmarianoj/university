package com.marianowinar.university.service.validation;

import java.util.regex.Pattern;

import com.marianowinar.university.service.entity.Account;
import com.marianowinar.university.service.exception.account.AccountException;
import com.marianowinar.university.service.exception.account.InvalidDniException;
import com.marianowinar.university.service.exception.account.InvalidPasswordAccountException;
import com.marianowinar.university.service.exception.account.NullAccountException;

public class ValidAccount extends Validator{

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
			throw new InvalidPasswordAccountException("Legajo del Password es nulo");
	}

	public void validateDni(String dni) throws InvalidDniException {
		if(dni == null)
			throw new InvalidDniException("DNI es nulo");
		
		if (!dni.chars().allMatch(Character::isDigit))
			throw new InvalidDniException("DNI no es numérico y debe ser solo números");
	}	
	
	public void validateLeg(String legajo) throws InvalidDniException {
		if(legajo == null)
			throw new InvalidDniException("El Legajo es nulo");
		
		if (!Pattern.matches("^([a-zA-ZñÑ0-9])+$", legajo))
			throw new InvalidDniException("El Legajo es invalido");
	}
	
}
