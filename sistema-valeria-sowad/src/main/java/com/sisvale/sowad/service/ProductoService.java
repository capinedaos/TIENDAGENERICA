package com.sisvale.sowad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sisvale.sowad.contracts.IProductoContract;
import com.sisvale.sowad.entity.Producto;
import com.sisvale.sowad.repository.IProductoRepository;

@Service
public class ProductoService implements IProductoContract{

	@Autowired
	private IProductoRepository _productoRepository;

	@Override
	@Transactional(readOnly=true)
	public List<Producto> findAll() {
		return (List<Producto>)_productoRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Producto findById(Long id) {
		return _productoRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		return _productoRepository.save(producto);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		_productoRepository.deleteById(id);
		
	}

	
}
