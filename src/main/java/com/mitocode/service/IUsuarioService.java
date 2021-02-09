package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Usuario;

public interface IUsuarioService {
	
	public Boolean usuarioRepetido(Usuario us);
	
	public List<Usuario> findAll();
	
	public Boolean findById(int id);
}
