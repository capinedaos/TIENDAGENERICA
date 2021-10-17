package com.sisvale.sowad.contracts;

import java.util.List;
import com.sisvale.sowad.entity.Cliente;

public interface IClienteContract {

	public List<Cliente> findAll();

	public Cliente findById(Long id);

	public Cliente save(Cliente cliente);

	public void delete(Long id);

}
