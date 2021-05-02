package com.marianowinar.university.service.exception.person;

@SuppressWarnings("serial")
public class PersonException extends Exception{
	
	protected int idError;
    protected String error;

    public PersonException() {
    }

    public PersonException(String message) {
        super(message);
    }

    public String getError(){
        return this.error;
    }

    protected void setError(String message){
        this.error = message;
    }
}
