package com.marianowinar.university.service.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marianowinar.university.repository.ProfessorRepository;
import com.marianowinar.university.service.entity.Material;
import com.marianowinar.university.service.entity.Person;
import com.marianowinar.university.service.entity.Professor;
import com.marianowinar.university.service.entity.source.AddMaterial;
import com.marianowinar.university.service.entity.source.Register;
import com.marianowinar.university.service.exception.person.PersonException;
import com.marianowinar.university.service.factory.FactoryEntities;
import com.marianowinar.university.service.interfaces.Services;
import com.marianowinar.university.service.logger.Errors;
import com.marianowinar.university.service.validation.ValidPerson;

@Service
public class ProfessorService implements Services<Professor>{

	@Autowired
	ProfessorRepository profRepo;
	
	@Autowired
	MaterialService matServ;
	
	private ValidPerson valPer;
	private Errors errors;
	private FactoryEntities factory;
	
	public ProfessorService() {
		this.valPer = ValidPerson.getInstance();
		this.errors = Errors.getInstance();
		this.factory = FactoryEntities.getInstance();
	}
	
	@Override
	public boolean create(Professor entity) {
		boolean res = false;
		
		try {
			valPer.validPerson(entity);
			profRepo.save(entity);
			res = true;
		}catch(PersonException e) {
			errors.logError(e.getError());
		}
		return res;
	}

	@Override
	public String update(Professor entity) {
		String message = "";
		boolean res = false;
		for(Professor ele : viewAll()) {
			if(ele.getId() == entity.getId()) {
				System.out.println("ID de Profesor " + " " + ele.getId());
				System.out.println("ID de la Entity " + " " + entity.getId());
				create(entity);
				message = "El Profesor fue modificado exitosamente en la BD!";
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
		profRepo.deleteById(id);
		if(existsById(id)) {
			return false;
		}
		return true;
	}

	@Override
	public List<Professor> viewAll() {
		return profRepo.findAll();
	}

	@Override
	public Professor getByName(String name) {
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		return profRepo.existsById(id);
	}

	@Override
	public boolean existsByObject(Professor entity) {
		return false;
	}

	/**
	 * Busca en la BD el objeto Professor con id
	 * @param id recibido de la web
	 * @return objeto professor encontrado
	 */
	public Professor searchingProfessor(Long id) {
		Professor prof = new Professor();
		for(Professor ele : viewAll()) {
			if(ele.getId() == id) {
				prof = ele;
				break;
			}
		}
		return prof;
	}

	/**
	 * Elimina un Profesor con el Id recibido por web
	 * @param id enviado por web del profesor
	 * @return mensaje con el resultado
	 */
	public String deleteProfessor(Long id) {
		String message = "";
		if(delete(id)) {
			message = "El Profesor fue eliminado exitosamente!!";
		}else {
			message = "No se ha podido eliminar el Profesor. Intente nuevamente.";
		}
		return message;
	}

	/**
	 * Crea un objeto Professor nuevo
	 * @param entity Datos enviados de la web
	 * @return mensaje con el resultado
	 */
	public String createProfessor(Professor entity) {
		String message = "";
		if(!searchProfNew(entity)) {
			Professor prof = factory.createProfessor(entity);
			if(create(prof)) {
				message = "El Profesor fue creado con éxito!!";
			}else {
				message = "No pudo ser creado el Profesor en la BD. Vuelva a intentarlo.";
			}
		}
		return message;
	}
	
	/**
	 * Busca si el profesor ya existe o no en BD
	 * @param entity Clase con datos de Registro nuevo
	 * @return true o false
	 */
	private boolean searchProfNew(Professor entity) {
		boolean res = false;
		for(Professor ele : viewAll()) {
			if(ele.getName().equals(entity.getName()) && ele.getSurname().equals(entity.getSurname())) {
				res = true;
				break;
			}
		}
		return res;
	}

	/**
	 * Funcion que Arma la Lista de Materiales a poder ser agregados a un profesor	 
	 * @param id del profesor 
	 * @return Lista completa
	 */
	public List<AddMaterial> createAddMaterial(Long id) {
		List<AddMaterial> addMatList = new ArrayList<>();		
		for(Material ele : matServ.viewAll()) {
			AddMaterial addMat = new AddMaterial();
			addMat.setPersonId(id);
			addMat.setMaterialId(ele.getId());
			addMat.setNameMaterial(ele.getName());
			addMat.setDetails(ele.getDetail());
			addMatList.add(addMat);
		}
		
		return addMatList;
	}

	@SuppressWarnings("unlikely-arg-type")
	public String deleteMaterial(Long id, Long ids) {
		Professor prof = searchingProfessor(id);
		prof.getMaterials().remove(ids);
		
		return update(prof);
	}

	public String addMaterial(Long idProfesor, Long id) {
		String message = "";
		Professor prof = searchingProfessor(idProfesor);
		Material mat = matServ.searchingMaterial(id);
		prof.addMaterial(mat);
		mat.addPerson(prof);
		message = matServ.update(mat);
		System.out.println("Mensaje update Material" + " " + message);
		message = update(prof);
		System.out.println("Mensaje update Professor" + " " + message);
		return message;
	}

}
