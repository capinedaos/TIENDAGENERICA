package com.sisvale.sowad.contracts;

import java.util.List;

import com.sisvale.sowad.entity.CompraProducto;
public interface IDetalleCompraContract {
	public List<CompraProducto> findAll();
	public CompraProducto findById(Long id);
	public CompraProducto save(CompraProducto CcompraProducto);
	public void delete(Long id);
}
