package com.marianowinar.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marianowinar.university.service.entity.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long>{

}
