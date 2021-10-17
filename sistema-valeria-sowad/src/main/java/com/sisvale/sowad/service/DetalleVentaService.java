package com.sisvale.sowad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import com.sisvale.sowad.contracts.IDetalleVentaContract;
import com.sisvale.sowad.entity.VentaPlato;
import com.sisvale.sowad.repository.IDetalleVentaRepository;

@Service
public class DetalleVentaService implements IDetalleVentaContract {

	@Autowired
	private IDetalleVentaRepository _detalleVentaRepository;
	
	@Override
	public List<VentaPlato> findAll() {
		return (List<VentaPlato>)_detalleVentaRepository.findAll();
	}

	@Override
	public VentaPlato findById(Long id) {
		
		return  _detalleVentaRepository.findById(id).orElse(null);
	}

	@Override
	public VentaPlato save(VentaPlato detalleVenta) {
		
		return _detalleVentaRepository.save(detalleVenta);
	}

	@Override
	public void delete(Long id) {
		_detalleVentaRepository.deleteById(id);
		
	}

	/*@Override
	@Transactional(readOnly=true)
	public VentaPlato listarPorVenta(Long id) {
		return _detalleVentaRepository.listarPorVenta(id);
	}*/

}
