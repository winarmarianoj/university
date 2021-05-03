package com.marianowinar.university.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
    UserDetailsServiceImpl userDetailsService;
	
	BCryptPasswordEncoder bCryptPasswordEncoder;

    //Necesario para evitar que la seguridad se aplique a los resources    
    String[] resources = new String[]{
            "/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**",
            "/fonts/**", "/logos/**"
    };
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
            .authorizeRequests()
	        .antMatchers(resources).permitAll()  
	        .antMatchers("/","/index","/registers","/forgot","/registrate","/resultRegistered").permitAll()
	        .antMatchers("/admin/*").access("hasRole('ADMIN') or hasRole('ROLE_ADMIN')")
	        .antMatchers("/user/*").access("hasRole('USER') or hasRole('ROLE_USER')")
	        	.and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/menu")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
            .logout()
                .permitAll()
                .logoutSuccessUrl("/login?logout");
    }
        
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {		
        return new BCryptPasswordEncoder(4);
    }
	
    //Registra el service para usuarios y el encriptador de contrasena
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
    	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());     
    }
}

/*
 * .antMatchers("/admin/deletesAdmin", "/admin/ListOrderStudent", "/admin/profileAdmin", "/admin/quotaMaterial",
	        		"/admin/responseAdmin", "/admin/updateAdmin", 
	        		"/material/materialControlPanel", "/material/matListProf", "/material/registerMaterial", "/material/respMaterial",
	        		"/material/updateMaterial",
	        		"/professor/professorAddMaterial", "/professor/professorControlPanel", "/professor/profListMat",
	        		"/professor/registerProfessor", "/professor/respProfessor", "/professor/updateProfessor").access("hasRole('ADMIN') or hasRole('ROLE_ADMIN')")
	        .antMatchers("/user/student/deletesUser", "/user/student/inscriptionStudent", "/user/student/listMaterialsSubscribed",
	        		"/user/student/listMaterialStudentinscripted", "/user/student/profileStudent", "/user/student/responseStudent",
	        		"/user/student/updateStudent").access("hasRole('USER') or hasRole('ROLE_USER')")
 * 
 * 
 * 
 */

