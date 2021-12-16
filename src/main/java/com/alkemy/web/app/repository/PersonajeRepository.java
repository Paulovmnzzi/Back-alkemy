package com.alkemy.web.app.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.web.app.entity.Casa;
import com.alkemy.web.app.entity.Personaje;

public interface PersonajeRepository extends JpaRepository<Personaje, Serializable> {

	public Personaje findByNombre(String nombre);
	
	public List<Personaje> findByEdad(Integer edad);
	
	public List<Personaje> findByCasa(Casa casa);
	
}
