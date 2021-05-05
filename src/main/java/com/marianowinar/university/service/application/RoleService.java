package com.marianowinar.university.service.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marianowinar.university.repository.RoleRepository;
import com.marianowinar.university.service.entity.Role;
import com.marianowinar.university.service.enums.RoleName;
import com.marianowinar.university.service.exception.role.RoleException;
import com.marianowinar.university.service.interfaces.Services;
import com.marianowinar.university.service.logger.Errors;
import com.marianowinar.university.service.validation.ValidRole;

@Service
@Transactional
public class RoleService implements Services<Role>{
	
	@Autowired
	RoleRepository roleRepo;
	
	private ValidRole validRole;
	private Errors errors;
	
	public RoleService() {
		this.validRole = ValidRole.getInstance();
		this.errors = Errors.getInstance();
	}

	@Override
	public String create(Role entity) {
		String result = "";
		try {
			validRole.validCreateRole(entity);
			roleRepo.save(entity);
			result = "El Role fue Salvada y Agregada exitosamente a BD!";
		}catch(RoleException e) {
			errors.logError(e.getError());
			result = e.getError() + " // " + "El Role No se pudo modificar o los datos son incorrectos.";
		}
		return result;
	}

	@Override
	public String update(Role entity) {
		return null;
	}

	@Override
	public boolean delete(Long id) {
		return false;
	}

	@Override
	public List<Role> viewAll() {
		return roleRepo.findAll();
	}

	@Override
	public boolean existsById(Long id) {
		return roleRepo.existsById(id);
	}	
	
	/*
	 * METHODS AND FUNCTION
	 */
	
	/**
	 * Busca si existe o no el objeto en BD
	 * @param role Objeto tipo Role 
	 * @return true o false
	 */
	public boolean existsByObject(Role role) {
		return roleRepo.existsByRole(role);
	}
	
	/**
	 * Busca un objeto por su nombre en la BD
	 * @param roleAdmin Objeto tipo RoleName enum
	 * @return el objeto RoleName
	 */
	public Optional<Role> getByRoleName(RoleName roleAdmin) {
		return roleRepo.findByRole(roleAdmin);
	}

	public Role takeRole(String name) {
		Role role = new Role();
		if(name.equals("ROLE_ADMIN")){
			role = roleRepo.findByRole(RoleName.ROLE_ADMIN).get();			
		}else {
			role = roleRepo.findByRole(RoleName.ROLE_USER).get();
		}		
		return role;
	}	

}
