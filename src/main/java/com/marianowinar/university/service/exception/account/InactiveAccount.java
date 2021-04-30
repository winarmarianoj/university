package com.marianowinar.university.service.exception.account;

import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class InactiveAccount extends AccountException{
    public InactiveAccount() {
        idError = 1;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + ": La cuenta de usuario no esta activa");
    }

}
