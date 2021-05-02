package com.marianowinar.university.service.entity.source;

public class Register {
	
	private String dni;
	private String legajo;
	private String name;
	private String surname;
	private String phone;
	private String email;
	private String type;
	
	public Register() {}
	
	public Register(String dni, String legajo, String name, String surname, String phone, String email, String type) {
		this.dni = dni;
		this.legajo = legajo;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.email = email;
		this.type = type;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}