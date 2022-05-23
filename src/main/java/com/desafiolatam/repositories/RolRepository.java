package com.desafiolatam.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafiolatam.models.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{

	List<Rol> findByNombre(String nombre);
}
