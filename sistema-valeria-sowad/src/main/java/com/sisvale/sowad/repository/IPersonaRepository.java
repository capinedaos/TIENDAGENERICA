package com.sisvale.sowad.repository;

import org.springframework.data.repository.CrudRepository;
import com.sisvale.sowad.entity.Persona;

public interface IPersonaRepository extends CrudRepository<Persona, Long>{
    
}
