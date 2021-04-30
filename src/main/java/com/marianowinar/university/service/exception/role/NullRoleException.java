package com.marianowinar.university.service.exception.role;

import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class NullRoleException extends RoleException{
	public NullRoleException(String role){
        idError = 13;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + " :" + role + " el role es nulo.");
    }
}
