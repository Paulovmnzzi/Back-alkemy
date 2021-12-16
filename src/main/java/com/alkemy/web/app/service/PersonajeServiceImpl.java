package com.alkemy.web.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.web.app.entity.Casa;
import com.alkemy.web.app.entity.Personaje;
import com.alkemy.web.app.repository.PersonajeRepository;

@Service
public class PersonajeServiceImpl {
	
	@Autowired
	private PersonajeRepository pr;
	
	@Autowired
	private CasaServiceImpl cs;
	
	public List<Personaje> findAll() {
		return pr.findAll();
	}
	
	public Personaje findByNombre(String nombre) {
		if(nombre != null) {
			Personaje personaje = pr.findByNombre(nombre);
			if(personaje != null) {
				return personaje;
			}
		}
		return null;
	}
	
	public List<Personaje> findByCasa(String nombreCasa){
		Casa casa = cs.findCasaByNombre(nombreCasa);
		if(casa != null) {
			List<Personaje> personajes = pr.findByCasa(casa);
			if(personajes != null) {
				return personajes;
			}
		}
		return null;
	}
	
	public Personaje save(Personaje personaje, String casaNombre) {
		if(casaNombre != null && personaje != null) {
			Casa casa = cs.findCasaByNombre(casaNombre);
			if(casa != null) {
				personaje.setCasa(casa);
				Personaje newPersonaje = pr.save(personaje);
				if(newPersonaje != null) {
					return newPersonaje;
				}
			}
		}
		return null;
	}
	
	
	public Personaje cargarParaeditarReino(String nombre) {
		Personaje personaje = pr.findByNombre(nombre);
		if(personaje != null) {
			return personaje;
		}
		return null;
	}
	
	
	public Personaje eliminarPersonaje(String nombre) {
		Personaje personaje = pr.findByNombre(nombre);
		if(personaje != null ) {
			pr.delete(personaje);
			return personaje;
		}
		return null;
	}
	

}
