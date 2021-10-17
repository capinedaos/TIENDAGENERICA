package com.sisvale.sowad.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.sisvale.sowad.entity.Usuario;

public interface IUsuarioRepository extends CrudRepository<Usuario, Long> {
	
	@Query("select u from Usuario u where u.usuario = ?1 and u.clave = ?2")
	Usuario findByNombreEndsWith(String usuario, String clave);
	
	@Query("select u from Usuario u where u.usuario = ?1")
	Usuario listarPorNombre(String nombre);

	

}
