package com.marianowinar.university.service.application;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marianowinar.university.service.entity.Role;
import com.marianowinar.university.service.entity.source.Register;
import com.marianowinar.university.repository.AccountRepository;
import com.marianowinar.university.service.entity.Account;
import com.marianowinar.university.service.enums.RoleName;
import com.marianowinar.university.service.exception.account.AccountException;
import com.marianowinar.university.service.factory.FactoryEntities;
import com.marianowinar.university.service.interfaces.Services;
import com.marianowinar.university.service.logger.Errors;
import com.marianowinar.university.service.util.PasswordEncryptor;
import com.marianowinar.university.service.validation.ValidAccount;

@Service
@Transactional
public class AccountService implements Services<Account>{
	
	@Autowired
	AccountRepository accRepo;
	
	@Autowired
	RoleService roleServ;
	
	@Autowired
	PersonService perServ;
	
	private ValidAccount valAcc;
	private Errors errors;
	private FactoryEntities factory;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public AccountService(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.valAcc = ValidAccount.getInstance();
		this.errors = Errors.getInstance();
		this.factory = FactoryEntities.getInstance();
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public boolean create(Account entity) {
		boolean res = false;
		try{			
			valAcc.validCreateAccount(entity);
			accRepo.save(entity);
			res = true;			
		}catch(AccountException e) {
			errors.logError(e.getError());
		}
		return false;
	}

	@Override
	public String update(Account entity) {
		String message = "";
		if(create(entity)) {
			message = "La Cuenta fue modificada exitosamente en la BD!";
		}else {
			message = "No se pudo modificar o los datos son incorrectos.";
		}
		return message;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Account> viewAll() {
		return accRepo.findAll();
	}
	
	@Override
	public boolean existsById(Long dni){
        return false;
    }
	
	/*
	 * METHODS AND FUNCTION
	 */

	/**
	 * Devuelve un user de la bd
	 * @param dni Numero de DNI del usuario
	 * @return objeto Account de la BD
	 */
	public Account takeUser(String dni) {
		Account account = new Account();
		for(Account ele : viewAll()) {
			if(ele.getUsername().equals(dni)) {
				account = ele;
				break;
			}
		}
		return account;
	}
		
	/*
	 * Se muestra los datos en el Menu y otros
	 */
	public Account findByUser(String name) {
		Account account = new Account();
		for(Account ele : viewAll()) {
			if(ele.getUsername().equals(name)) {
				account = ele;
				break;
			}
		}
		return account;
	}

	/**
	 * Desde el Form Register se crea Account nuevo
	 * @param entity Objeto Register que viene con los datos del Nuevo 
	 * @return un objeto Account nuevo
	 */
	public Account createUser(Register entity) {
		boolean res = false;
		Account account = userComplete(entity);
		
		if(!accRepo.existsByUsername(entity.getDni())) {	
			create(account);
		}		
		return account;
	}

	private Account userComplete(Register entity) {
		Account account = new Account();	
		Role role = new Role();
		
		account.setUsername(entity.getDni());
		account.setEnabled(true);
		account.setLegajo(entity.getLegajo());
		
		if(entity.getType().equals("Admin")) {
			role = roleServ.getByRoleName(RoleName.ROLE_ADMIN).get();
		}else {
			role = roleServ.getByRoleName(RoleName.ROLE_USER).get();
		}
		
		Set<Role> roles = new HashSet<>();
		roles.add(role);		
		account.setRoles(roles);
		
		String encodedPassword = bCryptPasswordEncoder.encode(entity.getLegajo());		
		account.setPassword(encodedPassword);
		
		return account;
	}

	/**
	 * Modifica la cuenta de Usuario
	 * @param entity Datos que vienen de la web
	 * @return mensaje con el resultado
	 */
	public String updateAccount(Register entity) {
		Account acc = findByUser(entity.getDni());
		acc.setEnabled(true);
		acc.setLegajo(entity.getLegajo());
		String encodedPassword = bCryptPasswordEncoder.encode(entity.getLegajo());		
		acc.setPassword(encodedPassword);		
		return update(acc);
	}

}

