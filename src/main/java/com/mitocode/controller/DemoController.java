package com.mitocode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mitocode.model.Usuario;
import com.mitocode.service.IUsuarioService;

@Controller
public class DemoController {
	
	@Autowired
	private IUsuarioService usServ;
	
	@GetMapping("/") //direccion en navegador web
	public String greeting(Usuario usuario) { // se envia el objeto usario para la vista			
		return "agregarUsuarios";	// nombre pagina html
	}
	
	@GetMapping("/verUsuarios")
	public String greeting(Model model) {
		model.addAttribute("usuarios", usServ.findAll()); // agregar datos pagina
		return "verUsuarios";
	}
	
	@PostMapping("/agregarUsuario")
	public String agregaUsuario(Usuario usuario, Model model, BindingResult binding) { // se quitó @Validated
		if(usServ.usuarioRepetido(usuario)) {
			model.addAttribute("usuarios", usServ.findAll());
			
			return "verUsuarios";
		} else {
			
			return "agregarUsuarios";
		}
	}
		
	@GetMapping("/borrarUsuario/{id}")
	public String borrarUsuario(@PathVariable("id") int id, Model model) {
		if(usServ.findById(id)) {
			model.addAttribute("usuarios", usServ.findAll());
		}
		
		return "verUsuarios";
	}

}
