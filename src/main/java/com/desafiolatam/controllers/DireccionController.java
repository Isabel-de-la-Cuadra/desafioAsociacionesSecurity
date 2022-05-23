package com.desafiolatam.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.desafiolatam.models.Direccion;
import com.desafiolatam.services.ClienteService;
import com.desafiolatam.services.DireccionService;

@Controller
@RequestMapping("/direccion")
public class DireccionController {

	@Autowired
	DireccionService direccionService;
	
	@Autowired
	ClienteService clienteService;
	
	//despliegue
		@RequestMapping("/cliente") // http://localhost:9080/direccion/cliente
		public String mostrarJSPDireccionCliente(@ModelAttribute("direccion") Direccion direccion, Model model) {
			model.addAttribute("listaClientes", clienteService.findAll());
			return "cliente/direccionCliente.jsp"; // Llamado al jsp u otra ruta
		}
		
		@PostMapping("/cliente/agregar")
		public String capturaDireccionCliente(@Valid @ModelAttribute("direccion") Direccion direccion, 
				BindingResult result, Model model, RedirectAttributes redirectAttributes) {
			
			if(!result.hasErrors()) { //no existe error
				//Validar si selecciono o no un usuario
				direccionService.save(direccion);
				redirectAttributes.addFlashAttribute("msgOk", "Dirección creada correctamente");
				return "redirect:/direccion/cliente";
			}else {//existe error capturado por el @Valid
				model.addAttribute("listaClientes", clienteService.findAll());
				return "cliente/direccionCliente.jsp"; // Llamado al jsp u otra ruta
			}
		}
}
