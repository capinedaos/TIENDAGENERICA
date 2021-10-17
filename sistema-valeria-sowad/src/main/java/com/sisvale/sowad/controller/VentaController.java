package com.sisvale.sowad.controller;

import java.io.IOException;
import com.sisvale.sowad.contracts.IClienteContract;
//import com.sisvale.sowad.contracts.IDetalleVentaContract;
import com.sisvale.sowad.contracts.IPlatoContract;
import com.sisvale.sowad.contracts.IVentaContract;
import com.sisvale.sowad.entity.Plato;
import com.sisvale.sowad.entity.Venta;
import com.sisvale.sowad.entity.VentaPlato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SessionAttributes("venta")
public class VentaController {

	@Autowired
	private IClienteContract _clienteContract;

	@Autowired
	private IPlatoContract _platoContract;

	@Autowired
	private IVentaContract _ventaContract;

	// @Autowired
	// private IDetalleVentaContract _detalleVenta;

	@RequestMapping(value = "mostraVenta", method = RequestMethod.GET)
	@GetMapping("/mostraVenta")
	public String prueba(Model model) {
	  return "/venta/venta";
	}

	@RequestMapping(value = "venta", method = RequestMethod.GET)
	@GetMapping("/venta")
	public String listar(Model model) {
		model.addAttribute("titulo", "Módulo de ventas");
		model.addAttribute("clientes", _clienteContract.findAll());
		model.addAttribute("platillos", _platoContract.findAll());
		return "/venta/venta";
	}

	@RequestMapping(value = "/venta")
	public String crear() {
		return "/venta/venta";
	}

	@RequestMapping(value = "/venta", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public Venta guardar(@RequestBody Venta venta) throws IOException {

		Venta ve = new Venta();

		for (VentaPlato v : venta.getVentaPlato()) {
			v.setVenta(venta);
			ve = _ventaContract.save(venta);
			_platoContract.actualizarStock(v.getCantidad(), v.getPlato().getId());
			System.out.println(v.getCantidad() + " " + ve.getId());

		}
		for (Plato pl : _platoContract.findAll()) {
			if (pl.getCantidad() == 0) {
				_platoContract.actualizarEstado(pl.getId());
			}
		}

		return ve;
	}

	@RequestMapping(value = "/contarVentas", method = RequestMethod.GET)
	public String contarVentas(Model model) throws IOException {
		// model.addAttribute("contarVentas",_ventaContract.contarVentas());
		// System.out.println(_ventaContract.contarVentas());
		return "menu";
	}

}
