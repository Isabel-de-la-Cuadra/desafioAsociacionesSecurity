package com.desafiolatam.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafiolatam.models.Producto;
import com.desafiolatam.repositories.ProductoRepository;

@Service
public class ProductoService {

	@Autowired
	ProductoRepository productoRepository;

	public Producto save(@Valid Producto producto) {
		return productoRepository.save(producto); 
	}

	public List<Producto> findAll() {
		return productoRepository.findAll();
	}

	public Producto findById(Long id) {
		return productoRepository.findById(id).get();
	}

}
