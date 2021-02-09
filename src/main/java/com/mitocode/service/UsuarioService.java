package com.mitocode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mitocode.model.Usuario;
import com.mitocode.repo.IUsuarioRepo;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	private IUsuarioRepo usRep;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public Boolean usuarioRepetido(Usuario us) {
		if(usRep.findByNombre(us.getNombre()) == null && 
				!us.getNombre().isEmpty() && !us.getClave().isEmpty()) {
			// codifica password
			us.setClave(encoder.encode(us.getClave())); 
			usRep.save(us);
			
			return true;
		}
			
		return false;
	}

	@Override
	public List<Usuario> findAll() {
		return usRep.findAll(Sort.by("nombre"));
	}

	@Override
	public Boolean findById(int id) {
		Optional<Usuario> us = usRep.findById(id);
		if(us.get() != null) {
			usRep.delete(us.get());
			
			return true;
		}		
		return false;
	}
}
