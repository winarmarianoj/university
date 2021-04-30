package com.marianowinar.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marianowinar.university.service.entity.Account;
import com.marianowinar.university.service.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	Person findByAccount(Account acc);
}