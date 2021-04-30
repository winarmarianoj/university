package com.marianowinar.university.service.exception.account;

import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class InvalidPasswordAccountException extends AccountException{
	public InvalidPasswordAccountException(String legajo) {
        idError = 4;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + " :" + legajo + ": El Legajo del suario es nulo");
    }

}
