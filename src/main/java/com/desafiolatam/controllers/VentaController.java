package com.desafiolatam.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.desafiolatam.models.Cliente;
import com.desafiolatam.models.Producto;
import com.desafiolatam.models.ProductosVentas;
import com.desafiolatam.models.Venta;
import com.desafiolatam.pdf.ProductoVentaPDFExporter;
import com.desafiolatam.pdf.VentaPDFExporter;
import com.desafiolatam.services.ClienteService;
import com.desafiolatam.services.ProductoService;
import com.desafiolatam.services.ProductoVentaService;
import com.desafiolatam.services.VentaService;
import com.lowagie.text.DocumentException;

@Controller
@RequestMapping("/venta")
public class VentaController {

	@Autowired
	VentaService ventaService;

	@Autowired
	ProductoService productoService;

	@Autowired
	ProductoVentaService productoVentaService;

	@Autowired
	ClienteService clienteService;

	// despliegue
	@RequestMapping("") // http://localhost:9080/venta
	public String showVenta(Model model) {
		model.addAttribute("ventaId", 0);
		model.addAttribute("listaProductos", productoService.findAll());
		return "venta/venta.jsp"; // Llamado al jsp u otra ruta
	}

	// Capturar
	@PostMapping("/agregar")
	public String agregarProducto(@RequestParam("productoId") Long productoId,
			@RequestParam("cantidad") Integer cantidad, @RequestParam("ventaId") Long ventaId, Model model) {
		System.out.println(productoId + cantidad + ventaId);
		if (productoId != null && productoId > 0) {
			if (cantidad != null && cantidad > 0) {
				// Obtener el producto con el id
				Producto producto = productoService.findById(productoId);

				// comparar el stock con cantidad
				if (cantidad > producto.getStock()) {
					model.addAttribute("msgError", "El stock no es suficiente para la compra");
				} else {// si hay stock se puede hacer la venta

					// calcular el monto de la venta
					Float precioProducto = producto.getPrecio();
					Float subtotalProducto = precioProducto * cantidad;
					Venta venta = new Venta();

					if (ventaId == 0) { // no existe, se crea

						// llenar un objeto venta
						venta.setMonto(subtotalProducto);

					} else {
						// si existe la venta, la obtengo, agrego la venta y sumo el monto total
						venta = ventaService.findById(ventaId);
						venta.setMonto(subtotalProducto + venta.getMonto());
					}

					// guardar el objeto en la base de datos y lo retorna actualizado
					venta = ventaService.save(venta);

					// Rebajar el stock
					producto.setStock(producto.getStock() - cantidad);
					productoService.save(producto);

					// retornar el nuevo ventaId al jsp
					model.addAttribute("ventaId", venta.getId());

					// Crear el objeto ProductosVentas
					ProductosVentas productosVentas = new ProductosVentas();
					productosVentas.setProducto(producto);
					productosVentas.setVenta(venta);
					productosVentas.setCantidad(cantidad);
					productosVentas.setValorUnitario(producto.getPrecio()); // valor histórico
					productosVentas.setTotal(subtotalProducto);

					// guardamos y relacionamos el producto con la venta
					productoVentaService.save(productosVentas);

					// Creamos una lista con la ventaId
					// método 01
					// List<ProductosVentas> listaProductosVentas =
					// productoVentaService.findAllByVentaId(venta.getId());
					// método 02
					List<ProductosVentas> listaProductosVentas = productoVentaService
							.findAllProductosVentas(venta.getId());

					// Pasamos información al jsp
					model.addAttribute("listaProductosVentas", listaProductosVentas);
					model.addAttribute("venta", venta);
				}
			} else {
				// no se agregó una cantidad
				model.addAttribute("msgError", "Debe ingresar una cantidad");
			}
		} else {
			// no se seleccionó un producto
			model.addAttribute("msgError", "Debe ingresar un producto");
		}
		model.addAttribute("listaClientes", clienteService.findAll());
		model.addAttribute("listaProductos", productoService.findAll());
		return "/venta/venta.jsp";
	}

	// desplegar
	@RequestMapping("/ver") // http://localhost:9080/venta/ver
	public String verVenta() {
		return "/venta/verVenta.jsp"; // Llamado al jsp u otra ruta
	}

	@PostMapping("/ver") // https://localhost:9080/venta/ver
	public String buscarVenta(@RequestParam("ventaId") Long ventaId, Model model) {
		Boolean existeVenta = ventaService.existsById(ventaId);
		System.out.println("Boolean " + existeVenta);
		if (!existeVenta) {
			model.addAttribute("msgError", "La venta no existe");
		} else {
			List<ProductosVentas> listaProductosVentas = productoVentaService.findAllProductosVentas(ventaId);

			// Pasamos información al jsp
			model.addAttribute("listaProductosVentas", listaProductosVentas);
			model.addAttribute("venta", ventaService.findById(ventaId));
		}
		return "/venta/verVenta.jsp"; // Llamado al jsp u otra ruta
	}

	// Capturar
	@PostMapping("/finalizar")
	public String finalizarVenta(@RequestParam("cliente") Long clienteId, @RequestParam("ventaId") Long ventaId) {

		Venta venta = ventaService.findById(ventaId);
		Cliente cliente = clienteService.obtenerCliente(clienteId);

		venta.setCliente(cliente);
		ventaService.save(venta);

		// generar el documento de venta
		return "redirect:/venta";
	}

	// Método eliminar por id de productosVentas
	@RequestMapping("/eliminar/{id}")
	public String eliminarVenta(@PathVariable("id") Long id, Model model) {
		ProductosVentas productosVentas = productoVentaService.findById(id);
		Venta venta = productosVentas.getVenta();
		venta.setMonto(venta.getMonto() - productosVentas.getTotal());
		venta = ventaService.save(venta);
		productoVentaService.deleteById(id);
		List<ProductosVentas> listaProductosVentas = productoVentaService.findAllProductosVentas(venta.getId());

		model.addAttribute("ventaId", venta.getId());
		model.addAttribute("venta", venta);
		System.out.println("venta " + venta.getId() + "ListaProductosVenta " + listaProductosVentas);
		model.addAttribute("listaProductosVentas", listaProductosVentas);
		model.addAttribute("listaProductos", productoService.findAll());
		model.addAttribute("listaClientes", clienteService.findAll());
		model.addAttribute("msgOK", "Producto eliminado de la venta");

		return "/venta/venta.jsp";
	}

	@GetMapping("/export/pdf")
	public void exportToPDFVentas(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=ventas_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		List<Venta> listaVenta = ventaService.findAll();

		VentaPDFExporter exporter = new VentaPDFExporter(listaVenta);
		exporter.export(response);

	}

	@GetMapping("/export/pdf/{id}")
	public void exportToPDFVenta(@PathVariable("id") Long ventaId, HttpServletResponse response)
			throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=venta_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);
		List<ProductosVentas> listaProductosVentas = productoVentaService.findAllProductosVentas(ventaId);
		Venta venta = ventaService.findById(ventaId);
		ProductoVentaPDFExporter exporter = new ProductoVentaPDFExporter(listaProductosVentas, venta);
		exporter.export(response);
	}

}
