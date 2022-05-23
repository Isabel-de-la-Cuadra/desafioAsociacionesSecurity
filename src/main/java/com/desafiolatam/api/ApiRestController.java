package com.desafiolatam.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.desafiolatam.models.Cliente;
import com.desafiolatam.models.Producto;
import com.desafiolatam.models.ProductosVentas;
import com.desafiolatam.models.Venta;
import com.desafiolatam.services.ClienteService;
import com.desafiolatam.services.ProductoService;
import com.desafiolatam.services.ProductoVentaService;
import com.desafiolatam.services.VentaService;

@RestController
@RequestMapping("/api")
public class ApiRestController {

	@Autowired
	ProductoService productoService;

	@Autowired
	VentaService ventaService;

	@Autowired
	ProductoVentaService productoVentaService;

	@Autowired
	ClienteService clienteService;

	@RequestMapping(value = "/producto/obtener", method = RequestMethod.GET)
	public List<Producto> obtenerProducto() {
		List<Producto> listaProductos = productoService.findAll();
		return listaProductos;
	}

	@RequestMapping(value = "/venta/obtener", method = RequestMethod.GET)
	public List<Venta> obtenerVenta() {
		List<Venta> listaVentas = ventaService.findAll();
		return listaVentas;
	}

	@RequestMapping(value = "/productoVenta/obtener", method = RequestMethod.GET)
	public List<ProductosVentas> obtenerProductoVenta() {
		List<ProductosVentas> listaProductosVentas = productoVentaService.findAll();
		return listaProductosVentas;
	}

	@RequestMapping(value = "/cliente/obtener", method = RequestMethod.GET)
	public List<Cliente> obtenerCliente() {
		List<Cliente> listaClientes = clienteService.findAll();
		return listaClientes;
	}

	@RequestMapping(value = "/producto/obtener/{id}", method = RequestMethod.GET)
	public Producto obtenerProductoId(@PathVariable("id") Long id) {
		Producto producto = productoService.findById(id);
		return producto;
	}

	@RequestMapping(value = "/venta/obtener/{id}", method = RequestMethod.GET)
	public Venta obtenerVentaId(@PathVariable("id") Long id) {
		Venta venta = ventaService.findById(id);
		return venta;
	}

}
