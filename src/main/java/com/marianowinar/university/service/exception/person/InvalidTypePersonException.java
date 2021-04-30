package com.marianowinar.university.service.exception.person;

import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class InvalidTypePersonException extends PersonException{
	public InvalidTypePersonException(String type){
        idError = 11;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + " :" + type + " en persona no es un tipo de usuario valido.");
    }
}
