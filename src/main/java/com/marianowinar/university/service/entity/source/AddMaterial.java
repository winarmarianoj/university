package com.marianowinar.university.service.entity.source;

public class AddMaterial {
	
	private Long personId;
	private Long materialId;
	private String nameMaterial;
	private String details;
	
	public AddMaterial() {	}

	public AddMaterial(Long personId, Long materialId, String nameMaterial, String details) {
		this.personId = personId;
		this.materialId = materialId;
		this.nameMaterial = nameMaterial;
		this.details = details;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public Long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}

	public String getNameMaterial() {
		return nameMaterial;
	}

	public void setNameMaterial(String nameMaterial) {
		this.nameMaterial = nameMaterial;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}	

}
