package com.desafiolatam.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.desafiolatam.models.Usuario;
import com.desafiolatam.services.UsuarioService;
import com.desafiolatam.services.VentaService;

@Controller
@RequestMapping("/")
public class UsuarioController {
	@Autowired
	UsuarioService usuarioService;

	@Autowired
	VentaService ventaService;

	@RequestMapping("/registro")
	public String registro(Model model, @ModelAttribute("usuario") Usuario usuario) {
		model.addAttribute("titulo", "Mi pagina");
		return "/usuario/registro.jsp";
	}

	@PostMapping("/registro")
	public String formulario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("msgError", "Debe ingresar todos los campos");
			return "/usuario/registro.jsp";
		} else {
			boolean resultado = usuarioService.guardarUsuario(usuario);
			if (resultado) {
				model.addAttribute("msgOk", "Ahora tienes que rellenar este formulario para entrar");
				return "redirect:/views/login";
			} else {
				model.addAttribute("msgError", "Correo ya existe");
				return "/usuario/registro.jsp";
			}
		}
	}

	/*
	 * Para asignar otros roles: - Hacer un jsp con selectores de usuarios y roles -
	 * Capturar el usuario y el rol en el controlador de usuario - Buscar usuario y
	 * rol por id en la BD - Agregar a la lista de roles del usuario el rol
	 * capturado usuario.getRoles().add(Rol) - Guardar el usuario
	 */

	// 1
	@RequestMapping("/usuario/login")
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model) {
		if (error != null) {
			model.addAttribute("msgError", "Credenciales Invalidas, Intenta nuevamente.");
		}
		if (logout != null) {
			model.addAttribute("logoutMessage", "¡Logout realizado!");
		}
		return "/views/login.jsp";

	}

	@RequestMapping(value = { "/","/homes" })
	public String home(Principal principal, HttpSession session) {
		// 1
		System.out.println("en RequestMapping /home ");
		String userEmail = principal.getName();
		session.setAttribute("usuarioEmail", userEmail);
		return "/views/bienvenida.jsp";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/usuario/login";
	}

}