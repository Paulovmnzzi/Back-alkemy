package com.alkemy.web.app.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "reinos")
public class Reino {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id_region")
	private Integer idReino;

	@Column(name = "nombre", unique = true, nullable = false)
	private String nombre;

	private String imagen;

	@JsonIgnoreProperties(value = "reino")
	@OneToMany(mappedBy = "reino")
	private Set<Casa> casas = new HashSet<Casa>();

	public Reino() {
		super();
	}

	public Reino(Integer idReino, String nombre, String imagen, Set<Casa> casas) {
		super();
		this.idReino = idReino;
		this.nombre = nombre;
		this.imagen = imagen;
		this.casas = casas;
	}

	public Reino(String nombre, String imagen, Set<Casa> casas) {
		super();
		this.nombre = nombre;
		this.imagen = imagen;
		this.casas = casas;
	}

	public Integer getIdReino() {
		return idReino;
	}

	public void setIdReino(Integer idReino) {
		this.idReino = idReino;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Set<Casa> getCasas() {
		return casas;
	}

	public void setCasas(Set<Casa> casas) {
		this.casas = casas;
	}

}
