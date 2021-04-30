package com.marianowinar.university.service.exception.person;

import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class NullPersonException extends PersonException {
	public NullPersonException() {
        idError = 9;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + ": Es nula la cuenta de Person");
    }	
}
