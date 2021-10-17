package com.sisvale.sowad.contracts;

import java.util.List;
import com.sisvale.sowad.entity.Venta;

public interface IVentaContract {
	public List<Venta> findAll();
	public Venta findById(Long id);
	public Venta save(Venta venta);
	public void delete(Long id);
	public void cambiarEstado(Long id);
	public int contarVenta();
	public int contarVentaPagada();
	public int contarVentaNoPagada();
	//public int contarVentas();
}
