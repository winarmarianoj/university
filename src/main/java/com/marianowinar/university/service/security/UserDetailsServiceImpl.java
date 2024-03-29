package com.marianowinar.university.service.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.marianowinar.university.repository.AccountRepository;
import com.marianowinar.university.service.entity.Account;
import com.marianowinar.university.service.entity.Role;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AccountRepository accRepo;
    
    private UserDetails users;
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	 
	    Account appUser = accRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));	     
	    List grantList = new ArrayList();
	    for (Role authority: appUser.getRoles()) {
	        // ROLE_USER, ROLE_ADMIN,..
	        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getRole().toString());
	            grantList.add(grantedAuthority);
	    }
	    
	    UserDetails user = (UserDetails) new User(appUser.getUsername(), appUser.getPassword(), grantList);
	    this.users = user;
	    
        return user;
    }

	public UserDetails getUsers() {
		return users;
	}

	public void setUsers(UserDetails users) {
		this.users = users;
	}
    
}
