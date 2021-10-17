package com.sisvale.sowad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sisvale.sowad.contracts.IPagoContract;
import com.sisvale.sowad.entity.Pago;
import com.sisvale.sowad.repository.IPagoRepository;

@Service
public class PagoService implements IPagoContract{

	@Autowired
	private IPagoRepository _pagoRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Pago> findAll() {
		
		return (List<Pago>)_pagoRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Pago findById(Long id) {
		
		return _pagoRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Pago save(Pago Pago) {
		
		return _pagoRepository.save(Pago);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		_pagoRepository.deleteById(id);
		
	}

}
