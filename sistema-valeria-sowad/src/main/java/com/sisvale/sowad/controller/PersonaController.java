package com.sisvale.sowad.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.context.annotation.SessionScope;
import com.sisvale.sowad.service.PersonaService;
import com.sisvale.sowad.entity.Persona;

@Controller
@RequestMapping
@SessionAttributes("persona")
public class PersonaController {

	//////////////////////////////////////////////////////////////
	// CRUD CLIENTES

	@Autowired
	private PersonaService servicePersona;

	@RequestMapping(value = "listarpersona", method = RequestMethod.GET)
	@GetMapping("/listarpersona")
	public String listarPersona(Model model) {
		List<Persona> personas = servicePersona.listarPersona();
		model.addAttribute("personas", personas);
		return "/cruds/persona";
	}

	@RequestMapping(value = "/newpersona", method = RequestMethod.GET)
	@GetMapping("/newpersona")
	public String agregarPersona(Model model) {
		model.addAttribute("persona", new Persona());
		return "/cruds/formPersona";
	}

	@PostMapping("/savepersona")
	public String savePersona(@Validated Persona p, Model model) {
		servicePersona.savePersona(p);
		return "redirect:/listarpersona";
	}

	@RequestMapping(value = "/editarpersona/{id}", method = RequestMethod.GET)
	@GetMapping("/editarpersona/{id}")
	public String editarPersona(@PathVariable long id, Model model) {
		Optional<Persona> persona = servicePersona.listarId(id);
		model.addAttribute("persona", persona);
		return "/cruds/formPersona";

	}

	@GetMapping("/eliminarpersona/{id}")
	public String deletePersona(Model model, @PathVariable long id) {
		servicePersona.deletePersona(id);
		return "redirect:/listarpersona";
	}

}
