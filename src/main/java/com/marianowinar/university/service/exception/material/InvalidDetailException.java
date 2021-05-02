package com.marianowinar.university.service.exception.material;

import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class InvalidDetailException extends MaterialException{

	public InvalidDetailException(String detail){
        idError = 18;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + " :" + detail + " de la Materia.");
    }
}
