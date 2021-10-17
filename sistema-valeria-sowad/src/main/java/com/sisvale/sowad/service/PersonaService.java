package com.sisvale.sowad.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sisvale.sowad.contracts.IPersonaContract;
import com.sisvale.sowad.entity.Persona;
import com.sisvale.sowad.repository.IPersonaRepository;

@Service
public class PersonaService implements IPersonaContract {

	@Autowired
	private IPersonaRepository data;

	@Override
	public List<Persona> listarPersona() {
		return (List<Persona>) data.findAll();
	}

	@Override
	public Optional<Persona> listarId(Long id) {
		return data.findById(id);
	}
	
	@Override
	public Long savePersona(Persona p) {
		long res = 0;
		Persona persona = data.save(p);
		if (!persona.equals(null)) {
			res = 1;
		}
		return res;
	}
	
	@Override
	public void deletePersona(Long id) {
		data.deleteById(id);
	
	}

}