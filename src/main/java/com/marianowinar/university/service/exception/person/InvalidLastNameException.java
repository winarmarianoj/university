package com.marianowinar.university.service.exception.person;

import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class InvalidLastNameException extends PersonException {

    public InvalidLastNameException(String lastName){
        idError = 8;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + " :" + lastName + " en persona no es un apellido valido.");
    }
}
