package com.sisvale.sowad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sisvale.sowad.contracts.IDetalleCompraContract;
import com.sisvale.sowad.entity.CompraProducto;
import com.sisvale.sowad.repository.IDetalleCompraRepository;

@Service
public class DetalleCompraService implements IDetalleCompraContract{

	@Autowired
	private IDetalleCompraRepository _detalleCompraRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<CompraProducto> findAll() {
		
		return (List<CompraProducto>)_detalleCompraRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public CompraProducto findById(Long id) {
		
		return _detalleCompraRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public CompraProducto save(CompraProducto CcompraProducto) {
		
		return _detalleCompraRepository.save(CcompraProducto);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		_detalleCompraRepository.deleteById(id);
		
	}

}
