package com.marianowinar.university.service.exception.account;

import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class ExistingAccountException extends AccountException{
	
	public ExistingAccountException(String text) {
        idError = 3;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error "+ idError +": Ya existe una cuenta de usuario vinculada a " + text);
    }

}
