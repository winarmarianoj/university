package com.marianowinar.university.service.exception.material;

import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class InvalidCapacityMaterialException extends MaterialException{
	
	public InvalidCapacityMaterialException(String capacity){
        idError = 16;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + " :" + capacity + " de la Materia.");
    }

}
