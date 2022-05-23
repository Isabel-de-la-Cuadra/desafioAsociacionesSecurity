package com.desafiolatam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafiolatam.models.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long>{

}
