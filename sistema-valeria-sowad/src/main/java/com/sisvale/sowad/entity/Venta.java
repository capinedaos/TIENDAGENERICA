package com.sisvale.sowad.entity;

//import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "venta")
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "fecha")
	private Date fecha;

	@Size(min = 6, max = 20)
	@Column(name = "estado")
	private String estado;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "id_venta_plato")
	@JsonBackReference
	private List<VentaPlato> ventaPlato = new ArrayList<>();

	public void addTag(VentaPlato tag) {
		ventaPlato.add(tag);

	}

	public Long getId() {
		return id;
	}

	public List<VentaPlato> getVentaPlato() {
		return ventaPlato;
	}

	public void setVentaPlato(List<VentaPlato> ventaPlato) {
		this.ventaPlato = ventaPlato;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	// Métodos
	public double calcularIgv() {
		double totalIgv = 0;
		totalIgv = calcularTotal() * 0.19;
		return totalIgv;
	}

	public double calcularTotal() {
		double total = 0;
		for (VentaPlato ventaPlat : ventaPlato) {
			total += ventaPlat.getPrecio() * ventaPlat.getCantidad();
		}
		return total;
	}

	public double calcularSubTotal() {
		double subTotal = 0;
		subTotal += calcularTotal() - calcularIgv();
		return subTotal;
	}

}
