package com.sisvale.sowad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "producto")
public class Producto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@Size(min=6,max=40)
	@Column(name="nombre")
	private String nombre;
	
	@Size(min=6,max=40)
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="precio")
	private double precio;
	
	@Size(min=6,max=20)
	@Column(name="estado")
	private String estado;
	
	@JsonIgnoreProperties ({ "hibernateLazyInitializer" , "handler" })
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_categoria")
	private Categoria categoria;

	@JsonIgnoreProperties ({ "hibernateLazyInitializer" , "handler" })
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_presentacion")
	private Presentacion presentacion;

	public Producto(long id, String nombre, String descripcion, double precio, String estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.estado = estado;
		}
	
	
		public Producto() {
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

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Presentacion getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(Presentacion presentacion) {
		this.presentacion = presentacion;
	}
	
	
	

}
