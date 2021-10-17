package com.sisvale.sowad.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sisvale.sowad.contracts.IClienteContract;
import com.sisvale.sowad.entity.Cliente;
import com.sisvale.sowad.repository.IClienteRepository;

@Service
public class ClienteService implements IClienteContract{

	@Autowired
	private IClienteRepository _clienteRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Cliente> findAll() {
		
		return (List<Cliente>)_clienteRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Cliente findById(Long id) {
		
		return _clienteRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return _clienteRepository.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		_clienteRepository.deleteById(id);
		
	}






















}
