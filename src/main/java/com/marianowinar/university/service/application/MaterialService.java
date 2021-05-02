package com.marianowinar.university.service.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marianowinar.university.repository.MaterialRepository;
import com.marianowinar.university.service.entity.Material;
import com.marianowinar.university.service.exception.material.MaterialException;
import com.marianowinar.university.service.interfaces.Services;
import com.marianowinar.university.service.logger.Errors;
import com.marianowinar.university.service.validation.ValidMaterial;

@Service
public class MaterialService implements Services<Material>{
	
	@Autowired
	MaterialRepository matRepo;
	
	private ValidMaterial valMat;
	private Errors errors;
	
	public MaterialService() {
		this.valMat = ValidMaterial.getInstance();
		this.errors = Errors.getInstance();
	}
	
	@Override
	public boolean create(Material entity) {
		boolean res = false;
		try {
			valMat.validNameMaterial(entity);
			matRepo.save(entity);
			res = true;
		}catch(MaterialException e) {
			errors.logError(e.getError());
		}
		return res;
	}

	@Override
	public String update(Material entity) {
		String message = "";
		boolean res = false;
		for(Material ele : viewAll()) {
			if(ele.getId() == entity.getId()) {
				create(entity);
				message = "La Materia fue modificada exitosamente en la BD!";
				res = true;
				break;
			}
		}
		
		if(!res) {
			message = "No se pudo modificar o los datos son incorrectos.";
		}
		return message;
	}

	@Override
	public boolean delete(Long id) {
		matRepo.deleteById(id);				
		if(!matRepo.existsById(id)) {
			return true;
		}
		return false;
	}

	@Override
	public List<Material> viewAll() {
		return matRepo.findAll();
	}
	
	@Override
	public boolean existsById(Long id) {		
		return matRepo.existsById(id);
	}

	/**
	 * Creadora de la Materia con los datos que vienen de la web
	 * @param entity objeto tipo Materia
	 * @return Mensaje de resultados
	 */
	public String createMaterial(Material entity) {
		String message = "";
		if(!matRepo.existsByName(entity.getName())) {
			create(entity);
			message = "La Materia fue creada con éxito!!";
		}else {
			message = "No pudo ser creada la Materia en la BD. Vuelva a intentarlo.";
		}
		return message;
	}

	/**
	 * Busca una Materia por ID
	 * @param id recibido desde la web
	 * @return objeto material encontrado
	 */
	public Material searchingMaterial(Long id) {
		Material mat = new Material();
		for(Material ele : viewAll()) {
			if(ele.getId() == id) {
				mat = ele;
				break;
			}
		}
		return mat;
	}

	/**
	 * Elimina una Materia con el id encontrado y enviado por web
	 * @param id recibido por web
	 * @return Mensaje de resultados
	 */
	public String deleteMaterial(Long id) {
		String message = "";
		if(delete(id)) {
			message = "La Materia fue eliminada exitosamente!!";
		}else {
			message = "No se ha podido eliminar la Materia. Intente nuevamente.";
		}
		return message;
	}
	
	/**
	 * Busca la Materia por su nombre en la BD
	 * @param name de la materia desde la web
	 * @return el objeto Material
	 */
	public Material getByName(String name) {
		return matRepo.findByName(name);
	}

}
