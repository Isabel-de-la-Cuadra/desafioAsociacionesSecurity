package com.desafiolatam.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="productos_ventas")
public class ProductosVentas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	//ManyToMany = 2 ManyToOne
	//1 ManyToOne FK
	@JsonBackReference
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "producto_id")
	private Producto producto;
	
	@NotNull
	//2 ManyToOne FK
	@JsonBackReference //Se coloca donde está la FK
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "venta_id")
	private Venta venta;
	
	@NotNull
	@Range(min=1, message = "Fuera del rango") //acepta 0,001
	private Integer cantidad;
	
	@NotNull
	@Range(min=1, message = "Fuera del rango") //acepta 0,001
	private Float valorUnitario; //valor histórico
	
	@NotNull
	@Range(min=1, message = "Fuera del rango") //acepta 0,001
	private Float total;
	
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
	public ProductosVentas() {
		super();
	}
	public ProductosVentas(@NotNull Producto producto, Venta venta,
			@NotNull @Range(min = 1, message = "Fuera del rango") Integer cantidad,
			@NotNull @Range(min = 1, message = "Fuera del rango") Float valorUnitario,
			@NotNull @Range(min = 1, message = "Fuera del rango") Float total) {
		super();
		this.producto = producto;
		this.venta = venta;
		this.cantidad = cantidad;
		this.valorUnitario = valorUnitario;
		this.total = total;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Venta getVenta() {
		return venta;
	}
	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Float getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(Float valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
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
