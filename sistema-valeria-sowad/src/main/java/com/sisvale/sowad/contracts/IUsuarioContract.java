package com.sisvale.sowad.contracts;

import java.util.List;
import com.sisvale.sowad.entity.Usuario;
import java.util.Optional;

public interface IUsuarioContract {

	public List<Usuario> findAll();

	public Usuario findByNombreEndsWith(String usuario, String clave);

	public Usuario listarPorNombre(String nombre);

	public Usuario findById(Long id);

	public Usuario save(Usuario usuario);

	public void delete(Long id);

	//////////////////////////////////////
	public List<Usuario> listarUsuario();

	public Optional<Usuario> listarId(Long id);

	public Long saveUsuario(Usuario u);

	public void deleteUsuario(Long id);
}
