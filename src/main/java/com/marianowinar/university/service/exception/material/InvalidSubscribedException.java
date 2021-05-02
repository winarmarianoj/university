package com.marianowinar.university.service.exception.material;

import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class InvalidSubscribedException extends MaterialException{

	public InvalidSubscribedException(String subscribed){
        idError = 17;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + " :" + subscribed + " de la Materia.");
    }
}
