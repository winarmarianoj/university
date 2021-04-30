package com.marianowinar.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marianowinar.university.service.entity.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
