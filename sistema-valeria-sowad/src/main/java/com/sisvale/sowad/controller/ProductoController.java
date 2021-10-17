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
import com.sisvale.sowad.service.PlatoService;
import com.sisvale.sowad.entity.Plato;

@Controller
@RequestMapping
@SessionAttributes("plato")
public class ProductoController {

    
	//////////////////////////////////////////////////////////////
	// CRUD PRODUCTOS

	@Autowired
	private PlatoService servicePlato;

	@RequestMapping(value = "listarplato", method = RequestMethod.GET)
	@GetMapping("/listarplato")
	public String listarPlato(Model model) {
		List<Plato> platos = servicePlato.listarPlato();
		model.addAttribute("platos", platos);
		return "/cruds/producto";
	}

	@RequestMapping(value = "/newplato", method = RequestMethod.GET)
	@GetMapping("/newplato")
	public String agregarPlato(Model model) {
		model.addAttribute("plato", new Plato());
		return "/cruds/formProducto";
	}

	@PostMapping("/saveplato")
	public String savePlato(@Validated Plato p, Model model) {
		servicePlato.savePlato(p);
		return "redirect:/listarplato";
	}

	@RequestMapping(value = "/editarplato/{id}", method = RequestMethod.GET)
	@GetMapping("/editarplato/{id}")
	public String editarPlato(@PathVariable long id, Model model) {
		Optional<Plato> plato = servicePlato.listarId(id);
		model.addAttribute("plato", plato);
		return "/cruds/formProducto";

	}

	@GetMapping("/eliminarplato/{id}")
	public String deletePlato(Model model, @PathVariable long id) {
		servicePlato.deletePlato(id);
		return "redirect:/listarplato";
	}
    
}
