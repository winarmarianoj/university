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
	public void update(Role entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Role> viewAll() {
		return roleRepo.findAll();
	}

	@Override
	public Role take(Long id) {
		return null;
	}

	@Override
	public Role getByName(String roleName) {
		return null;
	}

	@Override
	public boolean existsById(long id) {
		return roleRepo.existsById(id);
	}

	@Override
	public boolean existsByObject(Role role) {
		return roleRepo.existsByRole(role);
	}
	
	/*
	 * METHODS AND FUNCTION
	 */
		
	public Optional<Role> getByRoleName(RoleName roleAdmin) {
		return roleRepo.findByRole(roleAdmin);
	}	

}
