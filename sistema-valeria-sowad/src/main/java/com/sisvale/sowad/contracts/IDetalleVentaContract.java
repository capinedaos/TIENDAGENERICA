package com.sisvale.sowad.contracts;

import java.util.List;

import com.sisvale.sowad.entity.VentaPlato;
//import com.sisvale.sowad.entity.Plato;

public interface IDetalleVentaContract {
	public List<VentaPlato> findAll();
	public VentaPlato findById(Long id);
	public VentaPlato save(VentaPlato detalleVenta);
	public void delete(Long id);
	//public VentaPlato listarPorVenta(Long id);
}
