package com.alkemy.web.app.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "casas")
public class Casa {

	@Id
	private String nombre;

	@Column(nullable = true, unique = true)
	private String imagen;

	@Column(nullable = true, unique = true, length = 1000)
	private String historia;

//	@JsonIgnore
	@OneToMany(mappedBy = "casa")
	private List<Personaje> personajes = new ArrayList<Personaje>();

	@ManyToOne
	@JoinColumn(referencedColumnName = "id_region", name = "reino", nullable = false)
	private Reino reino;

	public Casa() {
	}

	public Casa(String nombre, String imagen, String historia, List<Personaje> personajes, Reino reino) {
		super();
		this.nombre = nombre;
		this.imagen = imagen;
		this.historia = historia;
		this.personajes = personajes;
		this.reino = reino;
	}

	public Reino getReino() {
		return reino;
	}

	public void setReino(Reino reino) {
		this.reino = reino;
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

	public String getHistoria() {
		return historia;
	}

	public void setHistoria(String historia) {
		this.historia = historia;
	}

	public List<Personaje> getPersonajes() {
		return personajes;
	}

	public void setPersonajes(List<Personaje> personajes) {
		this.personajes = personajes;
	};

}
