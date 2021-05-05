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
		
	public void validPerson(Person per) throws PersonException {}
	public void validCreateAccount(Account aco) throws AccountException {}
	public void validNameMaterial(Material mat) throws MaterialException {}
	public void validCreateRole(Role role) throws RoleException {}
}
