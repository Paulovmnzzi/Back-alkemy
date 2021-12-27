package com.alkemy.web.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alkemy.web.app.entity.Personaje;
import com.alkemy.web.app.service.PersonajeServiceImpl;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/personajes")
public class PersonajeController {

	@Autowired
	private PersonajeServiceImpl ps;

	@GetMapping("/listar")
	public ResponseEntity<?> obtenerPersonajes() {
		List<Personaje> personajes = ps.findAll();
		Map<String, Object> response = new HashMap<String, Object>();
		if (personajes != null) {
			return new ResponseEntity<List<Personaje>>(personajes, HttpStatus.OK);
		}
		response.put("error", "Todavía no existen personajes en esta lista");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/buscar/casa/{casa}")
	public ResponseEntity<?> obtenerPersonajesPorCasa(@PathVariable String casa) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<Personaje> personajes = ps.findByCasa(casa);
		if(casa == null || casa.isEmpty()) {
			response.put("error", "No ha ingresado una casa correctamente");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if (personajes != null) {
			return new ResponseEntity<List<Personaje>>(personajes, HttpStatus.OK);
		}
		response.put("error", "No existe una casa con este nombre o no hay personajes asociados");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/buscar/reino/{reino}")
	public ResponseEntity<?> obtenerPersonajesPorReino(@PathVariable(name = "reino") Integer Ideino){
		Map<String, Object> response = new HashMap<String, Object>();
		List<Personaje> personajes = ps.findByReino(Ideino);
		if(personajes != null) {
			return new ResponseEntity<List<Personaje>>(personajes, HttpStatus.OK);
		}else if(Ideino == null) {
			response.put("error", "No ha ingresado ningun reino para filtrar su busqueda");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		response.put("error", "No existe un reino con este nombre o no hay personajes asociados");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/buscar/nombre/{nombre}")
	public ResponseEntity<?> obtenerPersonajesPorNombre(@PathVariable String nombre){
		Map<String, Object> response = new HashMap<String, Object>();
		if(nombre == null) {
			response.put("error", "No ingreso un nombre correctamente");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}
		Personaje personaje = ps.findByNombre(nombre);
		if(personaje != null) {
			return new ResponseEntity<Personaje>(personaje, HttpStatus.OK);
		}
		response.put("error", "No se encontró el personaje solicitado");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/cargar/{nombre}") 
	public ResponseEntity<?> cargaParaEditar(@PathVariable String nombre) {
		Map<String, Object> response = new HashMap<String, Object>();
		Personaje personaje = ps.cargarParaeditarPersonaje(nombre);
		if (personaje != null) {
			return new ResponseEntity<Personaje>(personaje, HttpStatus.OK);
		}
		response.put("error", "No se encontró el personaje solicitado");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}

	@PostMapping("/agregar/{casa}")
	public ResponseEntity<?> agregarReino(@RequestBody Personaje personaje, @PathVariable String casa) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (personaje != null & casa != null) {
			Personaje newPersonaje = ps.save(personaje, casa);
			if (newPersonaje != null) {
				return new ResponseEntity<Personaje>(newPersonaje, HttpStatus.CREATED);
			}
			response.put("error", "No ingresó los datos del personaje correctamente");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Personaje>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/editar")
	public ResponseEntity<?> editarPersonaje(@RequestBody Personaje personajeAux){
		Map<String, Object> response = new HashMap<String, Object>();
		if ( personajeAux != null) {
			Personaje personaje = ps.editarPerosnaje(personajeAux);
			if(personaje != null) {
				return new ResponseEntity<Personaje>(personaje, HttpStatus.OK);
			}
			response.put("error", "No se encontró un personaje para editar con ese nombre o historia");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}
		response.put("error", "No ingresó los datos del personaje correctamente");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("delete/{nombre}")
	public ResponseEntity<?> eliminarReino(@PathVariable String nombre) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (nombre != null) {
			Personaje personaje = ps.eliminarPersonaje(nombre);
			if (personaje != null) {
				return new ResponseEntity<Personaje>(personaje, HttpStatus.OK);
			}
			response.put("error", "No se encontró el personaje solicitado");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		response.put("error", "No ingresó el nombre del personaje correctamente");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
	}

}
