package com.marianowinar.university.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marianowinar.university.service.entity.Role;
import com.marianowinar.university.service.enums.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	public boolean existsByRole(Role role);
	public Optional<Role> findByRole(RoleName roleAdmin);
}
