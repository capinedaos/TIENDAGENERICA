package com.sisvale.sowad.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.context.annotation.SessionScope;
import com.sisvale.sowad.service.ProveedorService;
import com.sisvale.sowad.entity.Proveedor;

@Controller
@RequestMapping
@SessionAttributes("proveedor")
public class ProveedorController {

	//////////////////////////////////////////////////////////////
	// CRUD PROVEEDOR

	@Autowired
	private ProveedorService serviceProveedor;

	@RequestMapping(value = "listarproveedor", method = RequestMethod.GET)
	@GetMapping("/listarproveedor")
	public String listarProveedor(Model model) {
		List<Proveedor> proveedores = serviceProveedor.listarProveedor();
		model.addAttribute("proveedores", proveedores);
		return "/cruds/proveedor";
	}

	@RequestMapping(value = "/newproveedor", method = RequestMethod.GET)
	@GetMapping("/newproveedor")
	public String agregarProveedor(Model model) {
		model.addAttribute("proveedor", new Proveedor());
		return "/cruds/formProveedor";
	}

	@PostMapping("/saveproveedor")
	public String saveProveedor(@Validated Proveedor p, Model model) {
		serviceProveedor.saveProveedor(p);
		return "redirect:/listarproveedor";
	}

	@RequestMapping(value = "/editarproveedor/{id}", method = RequestMethod.GET)
	@GetMapping("/editarproveedor/{id}")
	public String editarProveedor(@PathVariable long id, Model model) {
		Optional<Proveedor> proveedor = serviceProveedor.listarId(id);
		model.addAttribute("proveedor", proveedor);
		return "/cruds/formProveedor";

	}

	@GetMapping("/eliminarproveedor/{id}")
	public String deleteProveedor(Model model, @PathVariable long id) {
		serviceProveedor.deleteProveedor(id);
		return "redirect:/listarproveedor";
	}

}
