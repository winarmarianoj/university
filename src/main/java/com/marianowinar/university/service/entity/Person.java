package com.marianowinar.university.service.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	@NotBlank
	private String name;
	
	@Column
	@NotBlank
	private String surname;
	
	@Column
	@NotBlank
	private String phone;
	
	@Column
	@NotBlank
	private String email;
	
	@Column
	@NotBlank
	private String type;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "username")
	private Account account;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "persons_materials", 	joinColumns = { @JoinColumn(name = "personId", nullable = false, updatable = false) },	inverseJoinColumns = { @JoinColumn(name = "materialId", nullable = false, updatable = false) })
	private List<Material> materials;
	
	public Person() {}

	public Person(@NotBlank String name, @NotBlank String surname, @NotBlank String phone, @NotBlank String email,
			@NotBlank String type, Account account, List<Material> materials) {
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.email = email;
		this.type = type;
		this.account = account;
		this.materials = materials;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}
	
	// METHODS AND FUNCTION GAME LIST	

	public List<Material> getListMaterial() {
        if (materials == null){materials = new ArrayList<>();}
        return materials;
    }

    public void addMaterial(Material mat) {
    	if (materials == null){materials = new ArrayList<>();}
        materials.add(mat);
    }  
    public int listaMaterialSize(){
    	if (materials == null){materials = new ArrayList<>();}
        return materials.size();
    }
    public Material searchPerson(Long index){
        if (index < 0 || index >= listaMaterialSize()){return null;}
        Material aux = null;
        for(Material ele : materials) {
        	if(ele.getId() == index) {
        		aux = ele;
        	}
        }
        return aux;
    }
    public boolean removePerson(Long index){
    	if (index < 0 || index >= listaMaterialSize()){return false;}
        materials.remove(index);
        return true;
    }	
	
}