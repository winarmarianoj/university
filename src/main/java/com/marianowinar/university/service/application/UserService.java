package com.marianowinar.university.service.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marianowinar.university.repository.UserRepository;
import com.marianowinar.university.service.entity.Material;
import com.marianowinar.university.service.entity.Person;
import com.marianowinar.university.service.entity.source.Register;
import com.marianowinar.university.service.exception.person.PersonException;
import com.marianowinar.university.service.interfaces.Services;
import com.marianowinar.university.service.logger.Errors;
import com.marianowinar.university.service.validation.ValidPerson;

@Service
public class UserService implements Services<Person>{
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PersonService perServ;
	
	@Autowired
	AccountService accServ;
	
	@Autowired
	MaterialService matServ;
	
	private ValidPerson valPer;
	private Errors errors;
	
	public UserService() {
		this.valPer = ValidPerson.getInstance();
		this.errors = Errors.getInstance();
	}

	@Override
	public boolean create(Person entity) {
		boolean res = false;
		
		try {
			valPer.validPerson(entity);
			userRepo.save(entity);
			res = true;
		}catch(PersonException e) {
			errors.logError(e.getError());
		}
		return res;
	}

	@Override
	public String update(Person entity) {
		String message = "";
		if(create(entity)) {
			message = "Student fue modificado exitosamente en la BD!";
		}else {
			message = "No se pudo modificar o los datos son incorrectos.";
		}
		return message;
	}

	@Override
	public boolean delete(Long id) {
		userRepo.deleteById(id);
		if(existsById(id)) {
			return false;
		}
		return true;
	}

	@Override
	public List<Person> viewAll() {
		return userRepo.findAll();
	}

	@Override
	public boolean existsById(Long id) {
		return userRepo.existsById(id);
	}
	
	/*
	 * METHODS AND FUNCTION
	 */	

	/**
	 * Cambios en la Cuenta y Perfil del User(Student)
	 * @param entity Objeto Register con los datos de Account, Role y Person
	 * @return el Mensaje para mostrar por pantalla del resultado
	 */
	public String updateStudent(Register entity) {
		String message = accServ.updateAccount(entity);
		message = message + " " + perServ.updatePerson(entity);
		return message;
	}

	/**
	 * Función que Agrega una Materia a un Student
	 * @param stu Estudiante usuario conectado
	 * @param id del objeto Materia
	 * @return mensaje del resultado
	 */
	public String addMaterial(Person stu, Long id) {
		String message = "";
		Material mat = matServ.searchingMaterial(id);
		
		int capacity = Integer.parseInt(mat.getCapacity());
		int subscribed = Integer.parseInt(mat.getSubscribed());
		
		if(capacity > subscribed && !notSubscribed(stu,mat) && !distintHour(stu,mat)) {
			subscribed++;
			mat.setSubscribed(String.valueOf(subscribed));
			stu.addMaterial(mat);
			mat.addPerson(stu);
			message = matServ.update(mat);
			message = message + " " + update(stu);
		}else {
			message = "No puede inscribirse a la materia ya que se ha ocupado por completo el cupo de la misma, "
					+ "que es hasta " + " " + capacity + " " + " personas. Además puede ser que otra materia esté"
							+ "como esta a la misma hora y ya estas inscripto en ese horario para otra materia."
							+ "Revisa tu lista de materias inscriptos y vuelve a intentarlo en su defecto. Gracias.";
		}
		return message;
	}

	private boolean distintHour(Person stu, Material mat) {
		boolean res = false;
		for(Material ele : stu.getListMaterial()) {
			if(ele.getHour().equals(mat.getHour())) {
				res = true;
				break;
			}
		}
		return res;
	}

	private boolean notSubscribed(Person stu, Material mat) {
		boolean res = false;
		for(Material ele : stu.getListMaterial()) {
			if(ele.getName().equals(mat.getName())) {
				res = true;
				break;
			}
		}
		return res;
	}

	/**
	 * Función que Elimina una Materia a un Student
	 * @param stu Estudiante usuario conectado
	 * @param id del objeto Materia
	 * @return mensaje del resultado
	 */
	public String deleteMaterial(Person stu, Long id) {
		String message = "";
		Material mat = matServ.searchingMaterial(id);
		stu.getMaterials().remove(id);		
		mat.getListPerson().remove(stu.getId());
		
		int subscribed = Integer.parseInt(mat.getSubscribed());
		subscribed--;
		mat.setSubscribed(String.valueOf(subscribed));
		message = matServ.update(mat);
		message = message + " " + update(stu);
		return message;
	}

	/**
	 * Función que genera la Lista de Estudiantes por una
	 * materia en especial enviada por el id de la web
	 * @param id del objeto Material
	 * @return Lista de Estudiantes o Person
	 */
	public List<Person> listStudentForMaterial(Long id, Material mat) {
		List<Person> stuList = new ArrayList<>();
		for(Person ele : mat.getListPerson()) {
			if(ele.getType().equals("Student")) {
				stuList.add(ele);
			}
		}
		return stuList;
	}

}
