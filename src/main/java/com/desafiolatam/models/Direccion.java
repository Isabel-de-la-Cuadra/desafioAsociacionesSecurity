package com.desafiolatam.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "direcciones")
public class Direccion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min=4,message = "El mínimo de caracteres es 4")
	private String calle;
	
	@NotNull
	@Size(min=1, message = "Si no tiene número, coloca el 0")
	private String numero;
	
	private String poblacion;
	
	@NotNull
	@Size(min=4, message = "El mínimo de caracteres es 3")
	private String ciudad;
	
	@NotNull
	@Size(min=4, message = "El mínimo de caracteres es 4")
	private String comuna;
	
	@NotNull
	@Size(min=4, message = "El mínimo de caracteres es 4")
	private String region;

	// OneToOne, trae por default los join Eager (Activo, de inmediato) or Lazy
	// (Pasivo, lo hace cuando se lo piden)
	// se queda con la FK
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	public Direccion() {
		super();
	}

	public Direccion(@NotNull @Size(min = 4, message = "El mínimo de caracteres es 4") String calle,
			@NotNull @Size(min = 1, message = "Si no tiene número, coloca el 0") String numero, String poblacion,
			@NotNull @Size(min = 4, message = "El mínimo de caracteres es 3") String ciudad,
			@NotNull @Size(min = 4, message = "El mínimo de caracteres es 4") String comuna,
			@NotNull @Size(min = 4, message = "El mínimo de caracteres es 4") String region, Cliente cliente) {
		super();
		this.calle = calle;
		this.numero = numero;
		this.poblacion = poblacion;
		this.ciudad = ciudad;
		this.comuna = comuna;
		this.region = region;
		this.cliente = cliente;
	}

	public Direccion(Long id, String calle, String numero, String poblacion, String ciudad, String comuna,
			String region, Cliente cliente, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.calle = calle;
		this.numero = numero;
		this.poblacion = poblacion;
		this.ciudad = ciudad;
		this.comuna = comuna;
		this.region = region;
		this.cliente = cliente;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getComuna() {
		return comuna;
	}

	public void setComuna(String comuna) {
		this.comuna = comuna;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
