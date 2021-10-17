package com.sisvale.sowad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import com.sisvale.sowad.contracts.IPlatoContract;
import com.sisvale.sowad.entity.Plato;
import com.sisvale.sowad.repository.IPlatoRepository;

@Service
public class PlatoService implements IPlatoContract {

	@Autowired
	private IPlatoRepository _platoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Plato> findAll() {
		return (List<Plato>) _platoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Plato findById(Long id) {
		return _platoRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Plato save(Plato plato) {
		return _platoRepository.save(plato);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		_platoRepository.deleteById(id);

	}

	@Override
	@Transactional
	public void actualizarStock(int cantidad, Long id) {
		_platoRepository.actualizarStock(cantidad, id);
	}

	@Override
	@Transactional
	public void actualizarEstado(Long id) {
		_platoRepository.actualizarEstado(id);

	}

	///////////////////////////////////////////
	@Autowired
	private IPlatoRepository data;

	@Override
	public List<Plato> listarPlato() {
		return (List<Plato>) data.findAll();
	}

	@Override
	public Optional<Plato> listarId(Long id) {
		return data.findById(id);
	}

	@Override
	public Long savePlato(Plato p) {
		long res = 0;
		Plato plato = data.save(p);
		if (!plato.equals(null)) {
			res = 1;
		}
		return res;
	}

	@Override
	public void deletePlato(Long id) {
		data.deleteById(id);

	}

}
