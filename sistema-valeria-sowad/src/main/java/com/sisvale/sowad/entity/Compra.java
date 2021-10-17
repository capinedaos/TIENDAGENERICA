package com.sisvale.sowad.entity;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "compra")
public class Compra {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="fecha")
	private Date fecha;
	
	@Size(min=6,max=20)
	@Column(name="estado")
	private String estado;
	
	@JsonIgnoreProperties ({ "hibernateLazyInitializer" , "handler" })
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_proveedor")
	private Proveedor proveedor;
	
	@ManyToMany(cascade = { 
		    CascadeType.PERSIST, 
		    CascadeType.MERGE
		})
		@JoinColumn(name="id_compra_producto")
	private List<CompraProducto> compraProducto = new ArrayList<>();

	
	public List<CompraProducto> getCompraProducto() {
		return compraProducto;
	}

	public void setCompraProducto(List<CompraProducto> compraProducto) {
		this.compraProducto = compraProducto;
	}

	public Long getId() {
		return id;
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

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	// Métodos
		public double calcularIgv() {
			double totalIgv=0;
			totalIgv=calcularTotal()*0.18;
			return totalIgv;
		}
		public double calcularTotal() {
			double total=0;
			for(CompraProducto compraPro:compraProducto) {
				total+=compraPro.getPrecio()*compraPro.getCantidad();
			}
			return total;
		}
		public double calcularSubTotal() {
			double subTotal=0;
			subTotal+=calcularTotal() - calcularIgv();
			return subTotal;
		}

	
	
}
