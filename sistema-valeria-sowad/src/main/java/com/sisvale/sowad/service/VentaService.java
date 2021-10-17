package com.sisvale.sowad.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sisvale.sowad.contracts.IVentaContract;
import com.sisvale.sowad.entity.Venta;
import com.sisvale.sowad.repository.IVentaRepository;

@Service
public class VentaService implements IVentaContract{

	@Autowired
	private IVentaRepository _ventaRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Venta> findAll() {
		
		return (List<Venta>)_ventaRepository.findAll(); 
	}

	@Override
	@Transactional(readOnly=true)
	public Venta findById(Long id) {
		return _ventaRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Venta save(Venta venta) {
		return _ventaRepository.save(venta);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		_ventaRepository.deleteById(id);
		
	}

	@Override
	@Transactional
	public void cambiarEstado(Long id) {
		
		_ventaRepository.cambiarEstado(id);
	}

	@Override
	@Transactional(readOnly=true)
	public int contarVenta() {
		int contar=_ventaRepository.contarVenta();
		return contar;
	}

	@Override
	@Transactional(readOnly=true)
	public int contarVentaPagada() {
		
		int noPagado=_ventaRepository.contarVentaPagada();
		return noPagado;
	}

	@Override
	@Transactional(readOnly=true)
	public int contarVentaNoPagada() {
		int pagado=_ventaRepository.contarVentaNoPagada();
		return pagado;
	}

	/*@Override
	@Transactional
	public int contarVentas() {
		int ventas=_ventaRepository.contarVentas();
		return ventas;
	}*/

}
