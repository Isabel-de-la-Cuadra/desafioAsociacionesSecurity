package com.desafiolatam.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="productos")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String nombre;
	
	@NotNull
	private String descripcion;
	
	@NotNull
	@Range(min=1, message = "Fuera del rango") //acepta 0,001
	private Float precio;
	
	@NotNull
	@Range(min=1, message = "Fuera del rango") //acepta 0,001
	private Float stock;
	
	@JsonBackReference //Se coloca donde no está la FK
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable( name="productos_ventas",
	joinColumns = @JoinColumn(name = "producto_id"),
	inverseJoinColumns = @JoinColumn(name="venta_id")
	)
	private List<Venta> venta;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	@PrePersist
	protected void onCreate(){
	this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate(){
	this.updatedAt = new Date();
	}
	
	public Producto() {
		super();
	}
	public Producto(@NotNull String nombre, @NotNull String descripcion,
			@NotNull @Range(min = 1, message = "Fuera del rango") Float precio,
			@NotNull @Range(min = 1, message = "Fuera del rango") Float stock, List<Venta> venta) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.venta = venta;
	}
	public Producto(Long id, String nombre, String descripcion, Float precio, Float stock, List<Venta> venta,
			Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.venta = venta;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Float getPrecio() {
		return precio;
	}
	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	public Float getStock() {
		return stock;
	}
	public void setStock(Float stock) {
		this.stock = stock;
	}
	public List<Venta> getVenta() {
		return venta;
	}
	public void setVenta(List<Venta> venta) {
		this.venta = venta;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	} 
	
}

