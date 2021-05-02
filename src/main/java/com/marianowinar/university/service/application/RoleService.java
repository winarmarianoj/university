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
	public boolean create(Role entity) {
		boolean res = false;
		try {
			validRole.validCreateRole(entity);
			roleRepo.save(entity);
			res = true;
		}catch(RoleException e) {
			errors.logError(e.getError());
		}
		return res;
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

}
