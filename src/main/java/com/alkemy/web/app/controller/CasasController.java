package com.alkemy.web.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

import com.alkemy.web.app.entity.Casa;
import com.alkemy.web.app.service.CasaServiceImpl;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/casas")
public class CasasController {

	@Autowired
	private CasaServiceImpl cs;

	public final static Log LOG = LogFactory.getLog(CasasController.class);

	@GetMapping("/listar")
	public ResponseEntity<?> obtenerCasas() {
		List<Casa> casas = cs.obtenerCasas();
		Map<String, Object> response = new HashMap<String, Object>();
		if(casas != null){
		return new ResponseEntity<List<Casa>>(casas, HttpStatus.OK);
		}
		response.put("error", "Aun no se han agregado casas");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/buscar/nombre/{nombre}")
	public ResponseEntity<?> obtenerCasaPorNombre(@PathVariable(name = "nombre", required = true) String nombre) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (nombre != null) {
			Casa casa = cs.findCasaByNombre(nombre);
			if (casa == null) {
				response.put("error", "No se ha encontrado una casa con ese nombre");
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Casa>(casa, HttpStatus.OK);
			}
		}
		response.put("error", "Los datos ingresados no son correctos");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/buscar/reino/{reino}")
	public ResponseEntity<?> listarPorReino(@PathVariable Integer reino) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<Casa> casas = cs.findByReinoId(reino);
		if (casas != null && !casas.isEmpty()) {
			return new ResponseEntity<List<Casa>>(casas, HttpStatus.OK);
		}
		response.put("error", "No se han encontrado casas pertenecientes a ese reino");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
	}

	@PostMapping("/agregar/{reino_id}")
	public ResponseEntity<?> agregarCasa(@RequestBody Casa casa, @PathVariable Integer reino_id) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (casa != null && reino_id != null) {
			Casa newCasa = cs.save(casa, reino_id);
			if (newCasa != null) {
				return new ResponseEntity<Casa>(newCasa, HttpStatus.CREATED);
			}
		}
		response.put("error", "No se ha ingresado un formato válido de casa");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/editar")
	public ResponseEntity<?> editarCasa(@RequestBody Casa casa) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (casa != null) {
			Casa casaEditada = cs.editarCasa(casa);
			if (casaEditada != null) {
				return new ResponseEntity<Casa>(casaEditada, HttpStatus.OK);
			}
			response.put("error", "No se han encontrado una casa con ese nombre para editar");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		response.put("error", "No se ha ingresado un formato válido de casa");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/borrar/{nombre}")
	public ResponseEntity<?> borrarCasa(@PathVariable String nombre) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (nombre != null) {
			Casa casa = cs.removeCasa(nombre);
			if (casa != null) {
				return new ResponseEntity<Casa>(casa, HttpStatus.OK);
			}
			response.put("error", "No se han encontrado una casa con ese nombre para eliminar");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		response.put("error", "No se ha ingresado un formato válido de casa");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
	}

}
