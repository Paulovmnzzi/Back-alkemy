package com.alkemy.web.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "personajes")
public class Personaje {

	@Id
	private String nombre;

	private Integer edad;
	
	@Column(nullable = true, unique = true)
	private String imagen;

	@Column(nullable = true, unique = true, length = 3000)
	private String historia;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(referencedColumnName = "nombre", name = "casa")
	private Casa casa;

	public Personaje() {
	}

	public Personaje(String nombre, Integer edad, String imagen, String historia, Casa casa) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.imagen = imagen;
		this.historia = historia;
		this.casa = casa;
	}

	public Personaje(String nombre, Integer edad, String imagen, String historia) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.imagen = imagen;
		this.historia = historia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getHistoria() {
		return historia;
	}

	public void setHistoria(String historia) {
		this.historia = historia;
	}

	public Casa getCasa() {
		return casa;
	}

	public void setCasa(Casa casa) {
		this.casa = casa;
	}

}
