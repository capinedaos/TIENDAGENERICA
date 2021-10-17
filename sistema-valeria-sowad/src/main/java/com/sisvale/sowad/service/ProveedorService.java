package com.sisvale.sowad.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sisvale.sowad.contracts.IProveedorContract;
import com.sisvale.sowad.entity.Proveedor;
import com.sisvale.sowad.repository.IProveedorRepository;


@Service
public class ProveedorService  implements IProveedorContract{

	@Autowired
	private IProveedorRepository _proveedorRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Proveedor> findAll() {
		
		return (List<Proveedor>)_proveedorRepository.findAll();
		
	}

	@Override
	@Transactional(readOnly=true)
	public Proveedor findById(Long id) {
		return _proveedorRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Proveedor save(Proveedor proveedor) {
		return _proveedorRepository.save(proveedor);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		_proveedorRepository.deleteById(id);
	}

		///////////////////////////////////////////
		@Autowired
		private IProveedorRepository data;
	
		@Override
		public List<Proveedor> listarProveedor() {
			return (List<Proveedor>) data.findAll();
		}
		
		@Override
		public Optional<Proveedor> listarId(Long id) {
			return data.findById(id);
		}
		
		@Override
		public Long saveProveedor(Proveedor p) {
			long res = 0;
			Proveedor proveedor = data.save(p);
			if (!proveedor.equals(null)) {
				res = 1;
			}
			return res;
		}
		
		@Override
		public void deleteProveedor(Long id) {
			data.deleteById(id);
			
		}

	
}
