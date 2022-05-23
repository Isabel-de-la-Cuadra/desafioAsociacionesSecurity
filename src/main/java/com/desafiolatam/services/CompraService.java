package com.desafiolatam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafiolatam.models.Compra;
import com.desafiolatam.repositories.CompraRepository;

@Service
public class CompraService {

	@Autowired
	CompraRepository compraRepository;

	public Compra save(Compra compra) {
		return compraRepository.save(compra);
	}

	public List<Compra> findAll() {
		return compraRepository.findAll();
	}
	
}
