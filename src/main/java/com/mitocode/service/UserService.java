package com.mitocode.service;

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

import com.mitocode.model.Usuario;
import com.mitocode.repo.IUsuarioRepo;

@Service // estereotipo indica servicio
public class UserService implements UserDetailsService{// implementacion Spring, pedirá sobrescribir método

	@Autowired // inyección para BD
	private IUsuarioRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario us = repo.findByNombre(username);
		
		// GrantedAuthority de Spring Security define roles usuario
		List<GrantedAuthority> listRoles = new ArrayList<>();
		listRoles.add(new SimpleGrantedAuthority("ADMIN"));
		
		UserDetails userDet = new User(us.getNombre(), us.getClave(), listRoles);
		
		return userDet;
	}

}
