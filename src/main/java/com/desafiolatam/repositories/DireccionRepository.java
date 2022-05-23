package com.desafiolatam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafiolatam.models.Direccion;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long>{

}
