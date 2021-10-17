package com.sisvale.sowad.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sisvale.sowad.contracts.IUsuarioContract;
import com.sisvale.sowad.entity.Usuario;
import com.sisvale.sowad.repository.IUsuarioRepository;

@Service
public class UsuarioService implements IUsuarioContract {

	@Autowired // Estoy aqui esto es una prueba de lawcode
	private IUsuarioRepository _usuarioRepository;

	@Override
	@Transactional(readOnly = true) // ReadOnly si es una consulta select;
	public List<Usuario> findAll() {
		return (List<Usuario>) _usuarioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true) // ReadOnly si es una consulta select;
	public Usuario findByNombreEndsWith(String usuario, String clave) {
		return _usuarioRepository.findByNombreEndsWith(usuario, clave); // trolita akj
	}

	@Override
	@Transactional
	public Usuario findById(Long id) {
		return _usuarioRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		return _usuarioRepository.save(usuario);
	}

	@Override // Eliminar
	@Transactional
	public void delete(Long id) {
		_usuarioRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true) // ReadOnly si es una consulta select;
	public Usuario listarPorNombre(String nombre) {
		return _usuarioRepository.listarPorNombre(nombre);
	}

	///////////////////////////////////////////
	@Autowired
	private IUsuarioRepository data;

	@Override
	public List<Usuario> listarUsuario() {
		return (List<Usuario>) data.findAll();
	}
	
	@Override
	public Optional<Usuario> listarId(Long id) {
		return data.findById(id);
	}
	
	@Override
	public Long saveUsuario(Usuario u) {
		long res = 0;
		Usuario usuario = data.save(u);
		if (!usuario.equals(null)) {
			res = 1;
		}
		return res;
	}
	
	@Override
	public void deleteUsuario(Long id) {
		data.deleteById(id);
		
	}

}
