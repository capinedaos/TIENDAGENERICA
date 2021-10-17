package com.sisvale.sowad.contracts;

import java.util.List;
import com.sisvale.sowad.entity.Persona;
import java.util.Optional;

public interface IPersonaContract {

    public List<Persona> listarPersona();

	public Optional<Persona> listarId(Long id);

	public Long savePersona(Persona p);

	public void deletePersona(Long id);
    
}
