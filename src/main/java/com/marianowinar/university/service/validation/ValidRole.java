package com.marianowinar.university.service.validation;

import com.marianowinar.university.service.entity.Role;
import com.marianowinar.university.service.enums.RoleName;
import com.marianowinar.university.service.exception.role.InvalidRoleException;
import com.marianowinar.university.service.exception.role.NullRoleException;
import com.marianowinar.university.service.exception.role.RoleException;

public class ValidRole {
	
	private static ValidRole validRole;
	
	private ValidRole() {}
	
	public static ValidRole getInstance() {
		if(validRole == null) validRole = new ValidRole();
		
		return validRole;
	}
	
	public void validCreateRole(Role role) throws RoleException{
		if(role == null)
			throw new NullRoleException(null);
		
		validateRole(role.getRole());		
	}

	private void validateRole(String role) throws InvalidRoleException {
		if(!role.equals(RoleName.ROLE_ADMIN) && !role.equals(RoleName.ROLE_USER))
			throw new InvalidRoleException(role);
	}

}
