package com.mitocode.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.model.Usuario;
import com.mitocode.service.IUsuarioService;

@RestController
@RequestMapping("/usuarioRest")
public class RestDemoController {

	@Autowired	
	private IUsuarioService usServ;
	
	@GetMapping
	public List<Usuario> findAll(){
		return usServ.findAll();
	}
	
	@PostMapping
	public void guardar(@RequestBody Usuario us) {
		usServ.usuarioRepetido(us);
	}
}
