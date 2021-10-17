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
//import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="usuario")
	private String usuario;
	
	@Column(name="clave")
	private String clave;
	
	@Column(name="activo")
	private Boolean activo;
	
	@JsonIgnoreProperties ({ "hibernateLazyInitializer" , "handler" })
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_persona")
	private Persona persona;

	public Usuario(long id, String usuario, String clave, Boolean activo, Persona persona) {
	super();
	this.id = id;
	this.usuario = usuario;
	this.clave = clave;
	this.activo = activo;
	this.persona = persona;
	}


	public Usuario() {
	}


	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	
	
	

}
