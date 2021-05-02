package com.marianowinar.university.service.exception.material;

import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class InvalidHourException extends MaterialException{
	public InvalidHourException(String mess) {
        idError = 15;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + mess + ": de la Materia");
    }	
}
