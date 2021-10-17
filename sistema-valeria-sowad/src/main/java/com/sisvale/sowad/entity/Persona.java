package com.sisvale.sowad.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "persona")
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 6, max = 40)
	@Column(name = "nombres")
	private String nombres;

	@Size(min = 6, max = 40)
	@Column(name = "apellidos")
	private String apellidos;

	@Size(min = 6, max = 8)
	@Column(name = "dni")
	private int dni;

	@Column(name = "fechaNacimiento")
	private Date fechaNacimiento;

	@Column(name = "edad")
	private int edad;

	@Column(name = "email")
	private String email;

	@Column(name = "direccion")
	private String direccion;

	@JsonIgnoreProperties ({ "hibernateLazyInitializer" , "handler" })
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_persona")
	private Persona persona;

	public Persona(long id, String nombres, String apellidos, int dni, Date fechaNacimiento, String email,
			String direccion) {
		super();
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
		this.direccion = direccion;
	}

	public Persona() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
