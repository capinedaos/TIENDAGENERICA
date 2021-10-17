package com.sisvale.sowad.repository;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sisvale.sowad.entity.VentaPlato;

public interface IDetalleVentaRepository extends CrudRepository<VentaPlato, Long> {

	//@Query("select vp from VentaPlato vp inner join vp.id where vp.id=?1")
	//VentaPlato listarPorVenta(Long id);
}
