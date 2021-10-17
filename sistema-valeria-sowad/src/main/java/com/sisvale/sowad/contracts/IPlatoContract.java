package com.sisvale.sowad.contracts;

import java.util.List;
import com.sisvale.sowad.entity.Plato;
import java.util.Optional;

public interface IPlatoContract {
	public List<Plato> findAll();
	public Plato findById(Long id);
	public Plato save(Plato plato);
	public void delete(Long id);
	public void actualizarStock(int cantidad, Long id);
	public void actualizarEstado(Long id);


	//////////////////////////////////////
	public List<Plato> listarPlato();

	public Optional<Plato> listarId(Long id);

	public Long savePlato(Plato p);

	public void deletePlato(Long id);
}
