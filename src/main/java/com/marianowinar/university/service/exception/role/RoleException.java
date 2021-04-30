package com.marianowinar.university.service.exception.role;

@SuppressWarnings("serial")
public class RoleException extends Exception{
	
	protected int idError;
	protected String error;
	
	public RoleException() {}
	
	public RoleException(String message) {
        super(message);
    }

    public String getError(){
	    return this.error;
    }

    protected void setError(String message){
	    this.error = message;
    }

}
