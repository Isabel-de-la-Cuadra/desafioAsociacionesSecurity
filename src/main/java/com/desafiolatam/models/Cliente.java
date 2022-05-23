package com.desafiolatam.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@NotNull
	@Size(min=3, max=20, message = "Aceptamos nombres con más de 3 caracteres" )
	private String nombre;
	
	@NotNull
	@Size(min=3, max=20, message = "Aceptamos apellidos con más de 3 caracteres")
	private String apellido;
	
	@NotNull
	private String correo;
	
	//OneToOne, trae por default los join Eager (Activo, de inmediato) or Lazy (Pasivo, lo hace cuando se lo piden)
	//se mapea con cliente no con la tabla
	@OneToOne (mappedBy="cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Direccion direccion;

	//OneToMany si no tiene la FK es solo esto
	@JsonManagedReference
	@OneToMany (mappedBy="cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Compra> compras;
	
	//OneToMany si no tiene la FK es solo esto
	@JsonManagedReference
	@OneToMany (mappedBy="cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Venta> ventas;
	
	//updatable= false fecha en que se creó y no se va a poder modificar, a nivel de BD
	//DateTimeFormat en formato USA con la finalidad de poder hacer cálculos más fáciles
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt; 
	
	//PrePersist crea por nosotros antes de la persistencia
		@PrePersist
		protected void onCreate(){
			this.createdAt = new Date();
		}
			
		//PreUpdate crea por nosotros la fecha antes de la persistencia
		@PreUpdate
		protected void onUpdate(){
			this.updatedAt = new Date();
		}

		public Cliente() {
			super();
		}

		public Cliente(String nombre, String apellido, String correo, Direccion direccion, List<Compra> compras,
				List<Venta> ventas) {
			super();
			this.nombre = nombre;
			this.apellido = apellido;
			this.correo = correo;
			this.direccion = direccion;
			this.compras = compras;
			this.ventas = ventas;
		}
		
		public Cliente(Long id, String nombre, String apellido, String correo, Direccion direccion,
				List<Compra> compras, List<Venta> ventas, Date createdAt, Date updatedAt) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.apellido = apellido;
			this.correo = correo;
			this.direccion = direccion;
			this.compras = compras;
			this.ventas = ventas;
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

		public String getApellido() {
			return apellido;
		}

		public void setApellido(String apellido) {
			this.apellido = apellido;
		}

		public String getCorreo() {
			return correo;
		}

		public void setCorreo(String correo) {
			this.correo = correo;
		}

		public Direccion getDireccion() {
			return direccion;
		}

		public void setDireccion(Direccion direccion) {
			this.direccion = direccion;
		}

		public List<Compra> getCompras() {
			return compras;
		}

		public void setCompras(List<Compra> compras) {
			this.compras = compras;
		}

		public List<Venta> getVentas() {
			return ventas;
		}

		public void setVentas(List<Venta> ventas) {
			this.ventas = ventas;
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
