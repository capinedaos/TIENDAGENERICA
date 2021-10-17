package com.sisvale.sowad.contracts;

import java.util.List;
import com.sisvale.sowad.entity.Producto;


public interface IProductoContract {
	public List<Producto> findAll();
	public Producto findById(Long id);
	public Producto save(Producto producto);
	public void delete(Long id);

	




}
