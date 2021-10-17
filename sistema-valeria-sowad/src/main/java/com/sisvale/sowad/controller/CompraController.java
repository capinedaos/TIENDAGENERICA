package com.sisvale.sowad.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sisvale.sowad.contracts.ICompraContract;
import com.sisvale.sowad.contracts.IDetalleCompraContract;
import com.sisvale.sowad.contracts.IProductoContract;
import com.sisvale.sowad.contracts.IProveedorContract;
import com.sisvale.sowad.entity.Compra;
import com.sisvale.sowad.entity.CompraProducto;
//import com.sisvale.sowad.entity.Venta;
//import com.sisvale.sowad.entity.VentaPlato;

@Controller
@SessionAttributes("compra")
public class CompraController {

	@Autowired
	private IProveedorContract _proveedorContract;

	@Autowired
	private IProductoContract _productoContract;

	@Autowired
	private ICompraContract _compraContract;

	@Autowired
	private IDetalleCompraContract _detalleCompraContract;

	@RequestMapping(value = "compra", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Módulo de Compras");
		model.addAttribute("proveedores", _proveedorContract.findAll());
		model.addAttribute("productos", _productoContract.findAll());
		return "/compra/compra";
	}

	@RequestMapping(value = "/compra")
	public String crear() {
		return "/compra/compra";
	}

	@RequestMapping(value = "/compra", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public Compra guardar(@RequestBody Compra compra) throws IOException {

		Compra co = new Compra();
		for (CompraProducto c : compra.getCompraProducto()) {
			c.setCompra(compra);
		}
		co = _compraContract.save(compra);
		return co;
	}

	@RequestMapping(value = "/listarCompras")
	public String noPagado(Model model) {
		model.addAttribute("titulo", "Módulo de Compras");
		model.addAttribute("compras", _compraContract.findAll());
		return "/compra/listarCompras";
	}

	@RequestMapping(value = "/detalleCompra/{id}")
	public String detalleVenta(@PathVariable(value = "id") Long id, Map<String, Object> model, Model model2,
			RedirectAttributes f) {
		Compra compra = null;
		double total = 0;
		double subTotal = 0;
		double calcularIgv = 0;
		List<CompraProducto> compraProducto = new ArrayList<>();
		if (id > 0) {
			compra = _compraContract.findById(id);
			for (CompraProducto cp : _detalleCompraContract.findAll()) {
				if (cp.getCompra().getId() == id) {
					compraProducto.add(cp);
				}
				total = compra.calcularTotal();
				calcularIgv = compra.calcularIgv();
				subTotal = compra.calcularSubTotal();

			}
			if (compra == null) {
				f.addFlashAttribute("error", "El ID no existe en la base de datos");
				return "redirect:/noPagado";
			}
		} else {
			f.addAttribute("error", "El ID no puede ser 0");
			return "redirect:/noPagado";
		}
		model.put("compra", compra);
		model2.addAttribute("compraProductos", compraProducto);
		model.put("titulo", "Módulo de compra");
		model2.addAttribute("compraTotal", +total);
		model2.addAttribute("igv", +calcularIgv);
		model2.addAttribute("subTotal", +subTotal);
		return "/compra/detalleCompra";
	}

	@RequestMapping(value = "/imprimirCompra/{id}")
	public String imprimirCompra(@PathVariable(value = "id") Long id, Map<String, Object> model, Model model2,
			RedirectAttributes f) {
		Compra compra = null;
		double total = 0;
		double subTotal = 0;
		double calcularIgv = 0;
		List<CompraProducto> compraProducto = new ArrayList<>();
		if (id > 0) {
			compra = _compraContract.findById(id);
			for (CompraProducto cp : _detalleCompraContract.findAll()) {
				if (cp.getCompra().getId() == id) {
					compraProducto.add(cp);
				}
				total = compra.calcularTotal();
				calcularIgv = compra.calcularIgv();
				subTotal = compra.calcularSubTotal();

			}
			if (compra == null) {
				f.addFlashAttribute("error", "El ID no existe en la base de datos");
				return "redirect:/noPagado";
			}
		} else {
			f.addAttribute("error", "El ID no puede ser 0");
			return "redirect:/noPagado";
		}
		model.put("compra", compra);
		model2.addAttribute("compraProductos", compraProducto);
		model.put("titulo", "Módulo de compra");
		model2.addAttribute("compraTotal", +total);
		model2.addAttribute("igv", +calcularIgv);
		model2.addAttribute("subTotal", +subTotal);
		return "/compra/imprimirCompra";
	}

}
