package com.sisvale.sowad.contracts;

import java.util.List;
import com.sisvale.sowad.entity.Compra;

public interface ICompraContract {
	public List<Compra> findAll();
	public Compra findById(Long id);
	public Compra save(Compra Compra);
	public void delete(Long id);
	//public int contarVentas();
}
