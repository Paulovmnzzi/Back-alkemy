package com.alkemy.web.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.web.app.entity.Casa;
import com.alkemy.web.app.entity.Personaje;
import com.alkemy.web.app.entity.Reino;
import com.alkemy.web.app.repository.PersonajeRepository;
import com.alkemy.web.app.repository.ReinoRepository;

@Service
public class PersonajeServiceImpl {

	@Autowired
	private PersonajeRepository pr;

	@Autowired
	private CasaServiceImpl cs;

	@Autowired
	private ReinoRepository rr;

	public List<Personaje> findAll() {
		return pr.findAll();
	}

	public Personaje findByNombre(String nombre) {
		if (nombre != null) {
			Personaje personaje = pr.findByNombre(nombre);
			if (personaje != null) {
				return personaje;
			}
		}
		return null;
	}

	public List<Personaje> findByCasa(String nombreCasa) {
		Casa casa = cs.findCasaByNombre(nombreCasa);
		if (casa != null) {
			List<Personaje> personajes = pr.findByCasa(casa);
			if (personajes != null) {
				return personajes;
			}
		}
		return null;
	}

	public List<Personaje> findByReino(Integer idReino) {
		Optional<Reino> reinoOp = rr.findById(idReino);
		if (reinoOp.isPresent()) {
			Reino reino = reinoOp.get();
			if (reino != null && !reino.getCasas().isEmpty()) {
				List<Personaje> personajesPorReino = new ArrayList<Personaje>();
				for (Casa casa : reino.getCasas()) {
					if (casa.getPersonajes() != null) {
						for (Personaje personaje : casa.getPersonajes()) {
							personajesPorReino.add(personaje);
						}
					}
				}
			if (!personajesPorReino.isEmpty()) {
				return personajesPorReino;
			}
		}
		}
		return null;
	}

	public Personaje save(Personaje personaje, String casaNombre) {
		if (casaNombre != null && personaje != null) {
			Casa casa = cs.findCasaByNombre(casaNombre);
			if (casa != null) {
				personaje.setCasa(casa);
				Personaje newPersonaje = pr.save(personaje);
				if (newPersonaje != null) {
					return newPersonaje;
				}
			}
		}
		return null;
	}

	public Personaje cargarParaeditarPersonaje(String nombre) {
		Personaje personaje = pr.findByNombre(nombre);
		if (personaje != null) {
			return personaje;
		}
		return null;
	}

	public Personaje editarPerosnaje(Personaje personajeAux) {
		Personaje personajeBD = pr.findByNombre(personajeAux.getNombre());

		if (personajeAux != null && personajeBD != null) {
			personajeAux.setCasa(personajeBD.getCasa());
			Personaje personaje = pr.save(personajeAux);
			if (personaje != null) {
				return personaje;
			}
		}
		return null;
	}

	public Personaje eliminarPersonaje(String nombre) {
		Personaje personaje = pr.findByNombre(nombre);
		if (personaje != null) {
			pr.delete(personaje);
			return personaje;
		}
		return null;
	}

}
