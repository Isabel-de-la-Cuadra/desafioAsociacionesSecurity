package com.desafiolatam.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.desafiolatam.models.ProductosVentas;

@Repository
public interface ProductoVentaRepository extends JpaRepository<ProductosVentas, Long>{

	@Query(value="SELECT * FROM productos_ventas WHERE venta_id = ?1", nativeQuery=true)
	List<ProductosVentas> findAllProductosVentas(Long ventaId);
	
	@Query("SELECT pv FROM ProductosVentas pv WHERE pv.venta.id = ?1")
	List<ProductosVentas> findAllProductosVentasWhereVentaId(Long ventaId);
	
	@Query("SELECT pv FROM ProductosVentas pv WHERE pv.producto.id = ?1")
	List<ProductosVentas> findAllProductosVentasWhereProductoId(Long productoId);
	
}
