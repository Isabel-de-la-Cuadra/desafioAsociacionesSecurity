package com.desafiolatam.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.desafiolatam.models.Cliente;
import com.desafiolatam.services.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	

	// muestra el jsp
	@RequestMapping("") // http://localhost:9080/cliente
	public String showCliente(@ModelAttribute("cliente") Cliente cliente, Model model) { 
			model.addAttribute("listaClientes", clienteService.findAll());
			return "cliente/cliente.jsp"; 
	}


	// capturar los datos del jsp
	@PostMapping("")
	public String registrar(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		// pasar parámetro a otra url

		// validad
		if (!result.hasErrors()) {
			// persistencia
			clienteService.save(cliente);

			// pasar parámetro a otra ruta de controller, en este caso es un mensaje, pero
			// se puede pasar lo que se quiera
			redirectAttributes.addFlashAttribute("msgOk", "Cliente creado correctamente");

		} else {
			model.addAttribute("listaClientes", clienteService.findAll());
			// retornar mensaje de error y mantener en la página
			redirectAttributes.addFlashAttribute("msgError", "Faltan datos, por favor, reinténtalo");
			// Redirigir
			return "cliente/cliente.jsp";
		}

		// Redireccionamiento al mismo /cliente, pero con mensajes diferentes
		return "redirect:/cliente"; // redirigir
	}

	// método eliminar con captura dinámina
	// cliente/eliminar/${cliente.id}
	@RequestMapping("/eliminar/{id}")
	public String eliminarCliente(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		clienteService.eliminarPorId(id);
		redirectAttributes.addFlashAttribute("msgOk", "Cliente eliminado correctamente");
		return "redirect:/cliente";
	}

	// método editar
	// cliente/editar/${cliente.id}
	@RequestMapping("/editar/{id}")
	public String editarCliente(@PathVariable("id") Long id, Model model) {
		Cliente cliente = clienteService.findById(id);
		model.addAttribute("cliente", cliente);
		return "cliente/editarCliente.jsp";
	}

	// capturar los datos del jsp después de modificar
	@PostMapping("/actualizar")
	public String actualizarCliente(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		System.out.println("Cliente actualizado " + cliente.getId());
		if (!result.hasErrors()) {
			// persistencia
			clienteService.save(cliente);
			redirectAttributes.addFlashAttribute("msgOk", "Cliente actualizado correctamente");

		} else {
			// model.addAttribute("listaClientes", clienteService.findAll());
			// retornar mensaje de error y mantener en la página en este caso no nos sirve
			redirectAttributes.addFlashAttribute("msgError", "Faltan datos, por favor, reinténtalo");
			// Redirigir
			
			return "cliente/editarCliente.jsp";
		}

		// Redireccionamiento al mismo /cliente, pero con mensajes diferentes
		return "redirect:/cliente"; // redirigir
	}

	@RequestMapping("compras/{id}")
	public String cantidadCompras(@PathVariable("id") Long id, Model model) {
		Cliente cliente = clienteService.obtenerCliente(id);
		model.addAttribute("cliente", cliente);
		return "cliente/listaCompras.jsp";
	}
	
}
