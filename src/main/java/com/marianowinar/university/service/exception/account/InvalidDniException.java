package com.marianowinar.university.service.exception.account;

import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class InvalidDniException extends AccountException{
	public InvalidDniException(String dni){
        idError = 5;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + " :" + dni + " en el usuario no es un tipo de dni valido.");
    }
}
