package com.marianowinar.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marianowinar.university.service.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
}
