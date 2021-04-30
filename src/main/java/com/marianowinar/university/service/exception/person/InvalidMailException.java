package com.marianowinar.university.service.exception.person;

import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class InvalidMailException extends PersonException{
	
   public InvalidMailException(String email) {
        idError = 7;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + ": Cuenta invalida: " + email + " en person no es un email valido.");
    }

}
