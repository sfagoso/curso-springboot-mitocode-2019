package com.mitocode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mitocode.service.SecurityService;

@Configuration
@EnableWebSecurity //notación habilita spring web security
public class SecurityConfig extends WebSecurityConfigurerAdapter { //clase heredada, pedirá sobrescribir método para autenticación 
	
	@Autowired
	private SecurityService userServ;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	// objeto para encriptar
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCrypPasswordEncoder = new BCryptPasswordEncoder();
		return bCrypPasswordEncoder;
	}
	
	@Override // método sobre escrito
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// desencripta contraseña usuarios
		auth.userDetailsService(userServ).passwordEncoder(bcrypt);
	}
	
	// obliga peticiones autenticadas
	protected void config(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
		
		/*// original
		.authorizeRequests()
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
		*/
		
		/*
		http.csrf().disable().authorizeRequests()
		.anyRequest().authenticated()
		.and().httpBasic();
		*/
	}

}
