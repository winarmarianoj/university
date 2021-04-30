package com.marianowinar.university.service.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotBlank;

@Entity
@PrimaryKeyJoinColumn(name = "personId")
public class Professor extends Person{	
	
	@Column
	@NotBlank
	private boolean active;
	
	public Professor() {}
		
	public Professor(@NotBlank String name, @NotBlank String surname, @NotBlank String phone, @NotBlank String email,
			@NotBlank String type, Account account, List<Material> materials, boolean active) {
		super(name, surname, phone, email, type, account, materials);
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	
}