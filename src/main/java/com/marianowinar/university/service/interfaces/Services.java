package com.marianowinar.university.service.interfaces;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public interface Services<T> {
	public boolean create(T entity);
	public String update(T entity);
	public boolean delete(Long id);
	public List<T> viewAll();
	public T getByName(String name);	
	public boolean existsById(Long id);
	public boolean existsByObject(T entity);
}
