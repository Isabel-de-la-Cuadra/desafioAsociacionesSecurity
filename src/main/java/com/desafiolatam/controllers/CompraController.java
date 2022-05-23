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

import com.desafiolatam.models.Compra;
import com.desafiolatam.services.ClienteService;
import com.desafiolatam.services.CompraService;
import com.desafiolatam.services.DireccionService;

@Controller
@RequestMapping("/compra")
public class CompraController {
	
	@Autowired
	CompraService compraService;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	DireccionService direccionService;

		@RequestMapping("") // http://localhost:9080/compra
		public String showCompra(@ModelAttribute("compra") Compra compra, Model model) {
			model.addAttribute("listaClientes", clienteService.findAll());
			return "cliente/compra.jsp"; 
		}
	
		@PostMapping("")
		public String capturaCompra(@Valid @ModelAttribute("compra") Compra compra, 
				BindingResult result, Model model, RedirectAttributes redirectAttributes) {
			
			if(!result.hasErrors()) { //no existe error
				//Validar si selecciono o no un usuario
				compraService.save(compra);
				redirectAttributes.addFlashAttribute("msgOk", "Compra creada correctamente");
				return "redirect:/cliente";
			}else {//existe error capturado por el @Valid
				model.addAttribute("listaCompras", compraService.findAll());
				return "cliente/compra.jsp"; // Llamado al jsp u otra ruta
			}
		}
}
