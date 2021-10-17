package com.sisvale.sowad.contracts;

import java.util.List;
import com.sisvale.sowad.entity.Proveedor;
import java.util.Optional;

public interface IProveedorContract {
	public List<Proveedor> findAll();
	public Proveedor findById(Long id);
	public Proveedor save(Proveedor proveedor);
	public void delete(Long id);

		//////////////////////////////////////
		public List<Proveedor> listarProveedor();

		public Optional<Proveedor> listarId(Long id);
	
		public Long saveProveedor(Proveedor u);
	
		public void deleteProveedor(Long id);
	
}
