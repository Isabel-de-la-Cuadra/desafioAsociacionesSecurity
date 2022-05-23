package com.desafiolatam.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafiolatam.models.Direccion;
import com.desafiolatam.repositories.DireccionRepository;

@Service
public class DireccionService {
	
	@Autowired
	DireccionRepository direccionRepository;

	public void save(@Valid Direccion direccion) {
		direccionRepository.save(direccion);
	}

	public List<Direccion> findAll() {
		return direccionRepository.findAll();
	}
	
}