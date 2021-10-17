package com.sisvale.sowad.controller;

import java.util.Map;
import java.util.List;
import javax.validation.Valid;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
//import org.springframework.web.context.annotation.SessionScope;
import com.sisvale.sowad.contracts.IUsuarioContract;
import com.sisvale.sowad.service.UsuarioService;
import com.sisvale.sowad.entity.Usuario;

@Controller
@RequestMapping
@SessionAttributes("usuario")
public class UsuarioController {

	@Autowired
	private IUsuarioContract _usuarioContract;

	@RequestMapping(value = "/guardar")
	public String crear(Map<String, Object> model) {
		model.put("titulo", "Formulario del usuario");
		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		return "/usuario/guardar";
	}

	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Usuarios");
			return "/usuario/guardar";
		}
		_usuarioContract.save(usuario);
		status.setComplete(); // Elimina los objetos session del cliente
		return "/usuario/guardar";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid Usuario usuario, Model model, SessionStatus status) {
		try {

			Usuario usuarioLogin = _usuarioContract.findByNombreEndsWith(usuario.getUsuario(), usuario.getClave());
			if (usuarioLogin != null) {
				status.setComplete(); // Elimina los objetos session del cliente
				model.addAttribute("usuario", usuarioLogin.getPersona().getApellidos());
				return "redirect:menu";
			}
			model.addAttribute("failed", "¡Usuario y password inválidos!");
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return "login";

	}

	@RequestMapping(value = "/login")
	public String login(Map<String, Object> model) {
		model.put("titulo", "Tienda Mintic");
		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		return "login";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		if (id > 0) {
			_usuarioContract.delete(id);
		}
		return "redirect:/listar";
	}

	//////////////////////////////////////////////////////////////
	// CRUD USUARIOS

	@Autowired
	private UsuarioService serviceUsuario;

	@RequestMapping(value = "listarusuario", method = RequestMethod.GET)
	@GetMapping("/listarusuario")
	public String listarUsuario(Model model) {
		List<Usuario> usuarios = serviceUsuario.listarUsuario();
		model.addAttribute("usuarios", usuarios);
		return "/cruds/usuario";
	}

	@RequestMapping(value = "/newusuario", method = RequestMethod.GET)
	@GetMapping("/newusuario")
	public String agregarUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "/cruds/formUsuario";
	}

	@PostMapping("/saveusuario")
	public String saveUsuario(@Validated Usuario u, Model model) {
		serviceUsuario.saveUsuario(u);
		return "redirect:/listarusuario";
	}

	@RequestMapping(value = "/editarusuario/{id}", method = RequestMethod.GET)
	@GetMapping("/editarusuario/{id}")
	public String editarUsuario(@PathVariable long id, Model model) {
		Optional<Usuario> usuario = serviceUsuario.listarId(id);
		model.addAttribute("usuario", usuario);
		return "/cruds/formUsuario";

	}

	@GetMapping("/eliminarusuario/{id}")
	public String deleteUsuario(Model model, @PathVariable long id) {
		serviceUsuario.deleteUsuario(id);
		return "redirect:/listarusuario";
	}

	

}
