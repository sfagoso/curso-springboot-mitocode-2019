package com.mitocode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mitocode.model.Persona;
import com.mitocode.repo.IPersonaRepo;

@Controller
public class DemoController {
	
	@Autowired
	private IPersonaRepo repo;
	
	@GetMapping("/greeting") //nombre referencia, no tiene que ser el mismo de la página
	public String greeting(@RequestParam(name="name", required=false, defaultValue="Mundiola") String name, Model model) {
		//logica
		
		Persona p = new Persona();
		p.setIdPersona(2);
		p.setNombre("Code");
		repo.save(p);
		
		model.addAttribute("name", name);
		return "greeting";	// nombre de la pagina
	}
	
	@GetMapping("/listar")
	public String greeting(Model model) {
		//logica							
		model.addAttribute("personas", repo.findAll());
		return "greeting";	// nombre de la pagina
	}

}
