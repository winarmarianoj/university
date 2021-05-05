package com.marianowinar.university.service.interfaces;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface Services<T> {
	public String create(T entity);
	public String update(T entity);
	public boolean delete(Long id);
	public List<T> viewAll();
	public boolean existsById(Long id);
}
