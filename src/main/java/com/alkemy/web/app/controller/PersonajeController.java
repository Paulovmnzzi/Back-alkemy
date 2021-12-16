package com.alkemy.web.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alkemy.web.app.entity.Personaje;
import com.alkemy.web.app.service.PersonajeServiceImpl;

@Controller
@RequestMapping("/personajes")
public class PersonajeController {

	@Autowired
	private PersonajeServiceImpl ps;

	@GetMapping("/listar")
	public ResponseEntity<List<Personaje>> obtenerPersonajes() {
		List<Personaje> personajes = ps.findAll();
		if (personajes != null) {
			return new ResponseEntity<List<Personaje>>(personajes, HttpStatus.OK);
		}
		return new ResponseEntity<List<Personaje>>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/listar/{casa}")
	public ResponseEntity<List<Personaje>> obtenerPersonajesPorCasa(@PathVariable String casa) {
		List<Personaje> personajes = ps.findByCasa(casa);
		if (personajes != null) {
			return new ResponseEntity<List<Personaje>>(personajes, HttpStatus.OK);
		}
		return new ResponseEntity<List<Personaje>>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/cargar/{nombre}")
	public ResponseEntity<Personaje> cargaParaEditar(@PathVariable String nombre) {
		Personaje personaje = ps.cargarParaeditarReino(nombre);
		if (personaje != null) {
			return new ResponseEntity<Personaje>(personaje, HttpStatus.OK);
		}
		return new ResponseEntity<Personaje>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/agregar/{casa}")
	public ResponseEntity<Personaje> agregarReino(@RequestBody Personaje personaje, @PathVariable String casa) {
		if (personaje != null & casa != null) {
			Personaje newPersonaje = ps.save(personaje, casa);
			if (newPersonaje != null) {
				return new ResponseEntity<Personaje>(newPersonaje, HttpStatus.CREATED);
			}
			return new ResponseEntity<Personaje>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Personaje>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("delete/{nombre}")
	public ResponseEntity<Personaje> eliminarReino(@PathVariable String nombre) {
		if (nombre != null) {
			Personaje personaje = ps.eliminarPersonaje(nombre);
			if (personaje != null) {
				return new ResponseEntity<Personaje>(personaje, HttpStatus.OK);
			}
			return new ResponseEntity<Personaje>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Personaje>(HttpStatus.BAD_REQUEST);
	}

}
