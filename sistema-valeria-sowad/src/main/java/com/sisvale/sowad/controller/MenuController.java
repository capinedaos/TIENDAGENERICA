package com.sisvale.sowad.controller;
//import java.util.ArrayList;
//import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.sisvale.sowad.contracts.IClienteContract;
import com.sisvale.sowad.contracts.IVentaContract;
import com.sisvale.sowad.entity.Cliente;
import com.sisvale.sowad.entity.Venta;


@Controller
@SessionAttributes("menu")
public class MenuController {

	@Autowired
	private IClienteContract _clienteContract;
	
	@Autowired
	private IVentaContract _ventaContract;
	
	@RequestMapping(value="/menu")
	public String index(Model model) {
		int sumaClientes = 0;
	//	int sumaVentas = 0;

		for(Cliente cliente:_clienteContract.findAll()) {

			System.out.println(cliente.getId());
		}
		for(Venta venta:_ventaContract.findAll()) {

			System.out.println(venta.getId());
			
		}

		model.addAttribute("Tienda", "Generica");
		model.addAttribute("cantidadClientes", sumaClientes);
		model.addAttribute("cantidadVentas", _ventaContract.contarVenta());
		model.addAttribute("ventaPagada",_ventaContract.contarVentaPagada());
		model.addAttribute("ventaNoPagada",_ventaContract.contarVentaNoPagada());
		return "menu";
	}
	
}
