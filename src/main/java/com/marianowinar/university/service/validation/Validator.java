package com.marianowinar.university.service.validation;

import com.marianowinar.university.service.entity.Account;
import com.marianowinar.university.service.entity.Material;
import com.marianowinar.university.service.entity.Person;
import com.marianowinar.university.service.entity.Role;
import com.marianowinar.university.service.exception.account.AccountException;
import com.marianowinar.university.service.exception.material.MaterialException;
import com.marianowinar.university.service.exception.person.PersonException;
import com.marianowinar.university.service.exception.role.RoleException;

public abstract class Validator {

	protected static final int SUBSCRIPTION_MENOR = 0;
	protected static final int SUBSCRIPTION_MAYOR = 50;
	protected static final int CAPACITY_MENOR = 0;
	protected static final int CAPACITY_MAYOR = 50;
	protected static final int HOUR_MENOR = 0;
	protected static final int HOUR_MAYOR = 24;
	protected static final String ADMIN = "Admin";
	protected static final String USERS = "Student";
	protected static final String PROFESSOR = "Professor";
	protected static final String REGEX_PHONE = "^1(3|4|5|7|8)\\d{9}$";
	protected static final String REGEX_EMAIL = "^([a-zA-Z0-9-._ñ]+)@([a-zA-Z0-9-._ñ]+).([a-zA-Z]{2,5})$";
	protected static final String REGEX_NAMES = "^([a-zA-ZñÑ])+$";
	protected static final String REGEX_LEGAJO = "^([a-zA-ZñÑ0-9])+$";
		
	public void validPerson(Person per) throws PersonException {}
	public void validCreateAccount(Account aco) throws AccountException {}
	public void validNameMaterial(Material mat) throws MaterialException {}
	public void validCreateRole(Role role) throws RoleException {}
}
