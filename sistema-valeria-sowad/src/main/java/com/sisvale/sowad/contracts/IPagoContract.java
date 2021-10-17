package com.sisvale.sowad.contracts;

import java.util.List;
import com.sisvale.sowad.entity.Pago;

public interface IPagoContract {
	public List<Pago> findAll();
	public Pago findById(Long id);
	public Pago save(Pago Pago);
	public void delete(Long id);
}
