package com.desafiolatam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafiolatam.models.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{

}
