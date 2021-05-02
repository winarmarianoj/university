package com.marianowinar.university.service.exception.material;

import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class NullMaterialException extends MaterialException{
	public NullMaterialException() {
        idError = 14;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + ": Es nula la Materia");
    }	
}
