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

import com.desafiolatam.models.Producto;
import com.desafiolatam.services.ProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	@Autowired
	ProductoService productoService;
	
	//Despliegue jsp
	@RequestMapping("")
	public String showProducto(@ModelAttribute("producto") Producto producto) {
		return"/producto/crearProducto.jsp";
	}
	
	//Captura información jsp
	@PostMapping("")
	public String guardarProducto(@Valid @ModelAttribute("producto") Producto producto, 
			BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if(!result.hasErrors()) { //no existe error
			productoService.save(producto);
			redirectAttributes.addFlashAttribute("msgOk", "Producto creado correctamente");
			return "redirect:/producto";
		}else {//existe error capturado por el @Valid
			System.out.println("En el else del postMapping");
			return "producto/crearProducto.jsp"; // Llamado al jsp u otra ruta
		}
	}

}
