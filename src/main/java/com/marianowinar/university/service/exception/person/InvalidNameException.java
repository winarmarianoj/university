package com.marianowinar.university.service.exception.person;

import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class InvalidNameException extends PersonException{

    public InvalidNameException(String name){
        idError = 6;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + " :" + name + " en person no es un nombre valido.");
    }
}
