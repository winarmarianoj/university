package com.marianowinar.university.service.exception.person;

import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class InvalidPhoneException extends PersonException{

	public InvalidPhoneException(String phone){
        idError = 10;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + " :" + phone + " en persona no es un teléfono valido.");
    }
}
