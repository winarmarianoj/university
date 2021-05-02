package com.marianowinar.university.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.marianowinar.university.service.enums.RoleName;
import com.sun.istack.NotNull;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
	private RoleName role;
	
	public Role() {	}

	public Role(RoleName role) {
		this.role = role;
	}

	public RoleName getRole() {
		return role;
	}

	public void setRole(RoleName role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}	
	
}
