package com.mitocode.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mitocode.model.Usuario;
import com.mitocode.repo.IUsuarioRepo;

@Controller
public class DemoController {
	
	// @Autowired inyecta dependencias
	@Autowired 
	private IUsuarioRepo repoUs; // consultas en BD
	
	@Autowired
	private BCryptPasswordEncoder encoder; // para codificar password
		
	@GetMapping("/") //direccion en navegador web
	public String greeting(Usuario usuario) { // COMPROBAR SI QUITANDO PARAMETRO USUARIO SIGUE FUNCIONANDO			
		return "agregarUsuarios";	// nombre pagina html
	}
	
	@GetMapping("/verUsuarios")
	public String greeting(Model model) {
		model.addAttribute("usuarios", repoUs.findAll()); // agregar datos pagina
		return "verUsuarios";
	}
	
	@PostMapping("/agregarUsuario")
	public String agregaUsuario(Usuario usuario, Model model) {
		usuario.setClave(encoder.encode(usuario.getClave())); // codifica password
		repoUs.save(usuario);
		
		model.addAttribute("usuarios", repoUs.findAll());
		return "verUsuarios";
	}
	
	@GetMapping("/borrarUsuario/{id}")
	public String borrarUsuario(@PathVariable("id") int id, Model model) {
		Optional<Usuario> us = repoUs.findById(id); // busca/encuentra usuario
		repoUs.delete(us.get()); // borra usuario
		
		model.addAttribute("usuarios", repoUs.findAll());
		
		return "verUsuarios";
	}

}
