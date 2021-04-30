package com.marianowinar.university.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.marianowinar.university.service.application.RoleService;
import com.marianowinar.university.service.entity.Role;
import com.marianowinar.university.service.enums.RoleName;


@Service
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RoleService roleServ;

    @Override
    public void run(String... args) throws Exception {
        /*Role rolAdmin = new Role(RoleName.ROLE_ADMIN);
        Role rolUser = new Role(RoleName.ROLE_USER);
        roleServ.create(rolAdmin);
        roleServ.create(rolUser);*/
    }
}
