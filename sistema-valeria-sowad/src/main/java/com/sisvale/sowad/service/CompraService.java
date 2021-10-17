package com.sisvale.sowad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sisvale.sowad.contracts.ICompraContract;
import com.sisvale.sowad.entity.Compra;
import com.sisvale.sowad.repository.ICompraRepository;

@Service
public class CompraService implements ICompraContract{
	
	@Autowired
	private ICompraRepository _compraRepository;

	@Override
	@Transactional(readOnly=true)
	public List<Compra> findAll() {
	
		return (List<Compra>) _compraRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Compra findById(Long id) {
		
		return _compraRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Compra save(Compra compra) {
		
		return _compraRepository.save(compra);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		_compraRepository.deleteById(id);
		
	}

}
