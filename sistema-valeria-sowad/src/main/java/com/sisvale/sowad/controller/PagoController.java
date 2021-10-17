package com.sisvale.sowad.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import javax.validation.Valid;

import com.sisvale.sowad.contracts.IDetalleVentaContract;
import com.sisvale.sowad.contracts.IPagoContract;
import com.sisvale.sowad.contracts.IVentaContract;
import com.sisvale.sowad.entity.Pago;
import com.sisvale.sowad.entity.Venta;
import com.sisvale.sowad.entity.VentaPlato;

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

@Controller
@SessionAttributes("pago")
public class PagoController {

	@Autowired
	private IVentaContract _ventaContract;
	
	@Autowired
	private IPagoContract _pagoContract;
	
	@Autowired
	private IDetalleVentaContract _detalleVentaContract;
	
	@RequestMapping(value="/pago")
	public String crear(Model model) {	
		model.addAttribute("titulo","Módulo de pago");
		model.addAttribute("ventas",_ventaContract.findAll());
		return "/pago/pago";
	}
	
	@RequestMapping(value="/noPagado")
	public String noPagado(Model model) {	
		model.addAttribute("titulo","Módulo de pago");
		model.addAttribute("ventas",_ventaContract.findAll());
		return "/pago/noPagado";
	}
	
	@RequestMapping(value="/pagado")
	public String pagado(Model model) {	
		model.addAttribute("titulo","Módulo de pago");
		model.addAttribute("ventas",_ventaContract.findAll());
		return "/pago/pagado";
	}
	
	@RequestMapping(value="/pagar/{id}")
	public String pagarVenta(@PathVariable(value="id") Long id, Map<String,Object> model, Model model2, RedirectAttributes f) {	
		Venta venta=new Venta();
		Pago pago=new Pago();
		double total = 0;
		double subTotal = 0;
		double calcularIgv = 0;
		List<VentaPlato> ventaPlato=new ArrayList<>();
		if(id>0) {
			venta=_ventaContract.findById(id);
			for(VentaPlato vp:_detalleVentaContract.findAll()) {
				if(vp.getVenta().getId()==id) {
					ventaPlato.add(vp);
				}
				total=venta.calcularTotal();
				calcularIgv= venta.calcularIgv();
				subTotal = venta.calcularSubTotal();
			}
			if(venta==null ) {
				f.addFlashAttribute("error","El ID no existe en la base de datos");
				return "redirect:/noPagado";
			}
		}
		else {
			f.addAttribute("error","El ID no puede ser 0");
			return "redirect:/noPagado";
		}

		model.put("pago",pago);
		model.put("venta",venta);
		model2.addAttribute("ventaPlatos",ventaPlato);
		model.put("titulo","Módulo de pago");
		model2.addAttribute("ventaTotal",+total);
		model2.addAttribute("igv",+calcularIgv);
		model2.addAttribute("subTotal",+subTotal);
		
		return "/pago/pagar";
	}
	
	@RequestMapping(value="/detalleVenta/{id}")
	public String detalleVenta(@PathVariable(value="id") Long id, Map<String,Object> model, Model model2, RedirectAttributes f) {	
		Venta venta=null;
		double total = 0;
		double subTotal = 0;
		double calcularIgv = 0;
		List<VentaPlato> ventaPlato=new ArrayList<>();
		if(id>0) {
			venta=_ventaContract.findById(id);
			for(VentaPlato vp:_detalleVentaContract.findAll()) {
				if(vp.getVenta().getId()==id) {
					ventaPlato.add(vp);
				}
				total=venta.calcularTotal();
				calcularIgv= venta.calcularIgv();
				subTotal = venta.calcularSubTotal();
				
			}
			if(venta==null ) {
				f.addFlashAttribute("error","El ID no existe en la base de datos");
				return "redirect:/noPagado";
			}
		}
		else {
			f.addAttribute("error","El ID no puede ser 0");
			return "redirect:/noPagado";
		}
		model.put("venta",venta);
		model2.addAttribute("ventaPlatos",ventaPlato);
		model.put("titulo","Módulo de pago");
		model2.addAttribute("ventaTotal",+total);
		model2.addAttribute("igv",+calcularIgv);
		model2.addAttribute("subTotal",+subTotal);
		return "/pago/detalleVenta";
	}
	
	@RequestMapping(value="/imprimirVenta/{id}")
	public String imprimirVenta(@PathVariable(value="id") Long id, Map<String,Object> model, Model model2, RedirectAttributes f) {	
		Venta venta=null;
		double total = 0;
		double subTotal = 0;
		double calcularIgv = 0;
		List<VentaPlato> ventaPlato=new ArrayList<>();
		if(id>0) {
			venta=_ventaContract.findById(id);
			for(VentaPlato vp:_detalleVentaContract.findAll()) {
				if(vp.getVenta().getId()==id) {
					ventaPlato.add(vp);
				}
				total=venta.calcularTotal();
				calcularIgv= venta.calcularIgv();
				subTotal = venta.calcularSubTotal();
				
			}
			if(venta==null ) {
				f.addFlashAttribute("error","El ID no existe en la base de datos");
				return "redirect:/noPagado";
			}
		}
		else {
			f.addAttribute("error","El ID no puede ser 0");
			return "redirect:/noPagado";
		}
		model.put("venta",venta);
		model2.addAttribute("ventaPlatos",ventaPlato);
		model.put("titulo","Módulo de pago");
		model2.addAttribute("ventaTotal","$ "+total);
		model2.addAttribute("igv","$ "+calcularIgv);
		model2.addAttribute("subTotal","$ "+subTotal);
		return "/pago/imprimirPago";
	}

	
	@RequestMapping(value="/guardarPago", method=RequestMethod.POST, produces= "application/json", consumes = "application/json")
	@ResponseBody
	public Pago guardarPago(@RequestBody Pago pago)  throws IOException{	
		Pago p=_pagoContract.save(pago);

		_ventaContract.cambiarEstado(p.getVenta().getId());

		return p;
	}
	
}
