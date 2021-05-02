package com.marianowinar.university.service.exception.role;

import java.time.LocalDateTime;

import com.marianowinar.university.service.enums.RoleName;

@SuppressWarnings("serial")
public class InvalidRoleException extends RoleException{
	public InvalidRoleException(RoleName role){
        idError = 12;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + " :" + role + " no es un rol valido.");
    }
}
