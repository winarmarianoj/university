package com.marianowinar.university.service.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marianowinar.university.repository.MaterialRepository;
import com.marianowinar.university.service.entity.Material;
import com.marianowinar.university.service.entity.Person;
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
	public String create(Material entity) {
		String result = "";
		try {
			valMat.validNameMaterial(entity);
			matRepo.save(entity);
			result = "La Materia fue Salvada y Agregada exitosamente a BD!";
		}catch(MaterialException e) {
			errors.logError(e.getError());
			result = e.getError() + " // " + "La Materia No se pudo modificar o los datos son incorrectos.";
		}
		return result;
	}

	@Override
	public String update(Material entity) {
		String message = "";
		for(Material ele : viewAll()) {
			if(ele.getId() == entity.getId()) {
				message = create(entity);
				break;
			}
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

	/**
	 * Función que crea una lista de profesores por la materia seleccionada
	 * @param material objeto Material seleccionado
	 * @return lista de profesores asignados
	 */
	public List<Person> createListProf(Material material) {
		List<Person> profList = new ArrayList<>();
		for(Person ele : material.getListPerson()) {
			if(ele.getType().equals("Professor")) {
				profList.add(ele);
			}
		}
		return profList;
	}

	/**
	 * Función que crea una lista de estudiantes por la materia seleccionada
	 * @param material objeto Material seleccionado
	 * @return lista de estudiantes inscriptos
	 */
	public List<Person> createListStudent(Material material) {
		List<Person> stuList = new ArrayList<>();
		for(Person ele : material.getListPerson()) {
			if(ele.getType().equals("Student")) {
				stuList.add(ele);
			}
		}
		return stuList;
	}

}
