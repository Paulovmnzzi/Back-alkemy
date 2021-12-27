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

import com.alkemy.web.app.entity.Reino;
import com.alkemy.web.app.service.ReinoServiceImpl;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/reinos")
public class ReinoController {
	
	@Autowired
	private ReinoServiceImpl rs;

	@GetMapping("/listar")
	public ResponseEntity<?> obtenerReinos(){
		List<Reino> reinos = rs.findAll();
		Map<String, Object> response = new HashMap<String, Object>();
		if(reinos != null) {
			return new ResponseEntity<List<Reino>>(reinos, HttpStatus.OK);
		}
		response.put("error", "Aún no existen reinos");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/cargar/{id}")
	public ResponseEntity<?> cargaParaEditar(@PathVariable Integer id){
		Reino reino = rs.cargarParaeditarReino(id);
		Map<String, Object> response = new HashMap<String, Object>();
		if(id == null) {
			response.put("error", "No ha ingresado un id correcto");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if( reino != null ) {
			return new ResponseEntity<Reino>(reino, HttpStatus.OK);
		}
		response.put("error", "No se encontró un reino con ese nombre");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregarReino(@RequestBody Reino reino){
		Map<String, Object> response = new HashMap<String, Object>();
		if(reino != null) {
			Reino newReino = rs.nuevoReino(reino);
			if(newReino != null) {
				return new ResponseEntity<Reino>(newReino, HttpStatus.CREATED);
			}
			response.put("error", "No un reino correctamente");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CONFLICT);
		}
		response.put("error", "No se pudo agregar el reino");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
	}
	
	
	@PutMapping("/editar")
	public ResponseEntity<Reino> editarReino(@RequestBody Reino reino) {
		if(reino != null) {
			Reino newReino = rs.nuevoReino(reino);
			if(newReino != null){
				return new ResponseEntity<Reino>(newReino, HttpStatus.OK);
			}
			return new ResponseEntity<Reino>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Reino>(HttpStatus.NOT_FOUND);
	}
	
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> eliminarReino(@PathVariable Integer id){
		Map<String, Object> response = new HashMap<String, Object>();
		if(id != null) {
			Reino reino = rs.eliminarReino(id);
			if( reino != null) {
				return new ResponseEntity<Reino>(reino, HttpStatus.OK);
			}
			response.put("error", "No se ha encontrado un reino con ese nombre");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		response.put("error", "La información del reino no ha sido entregada correctamente");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
	}
	
	
	
	
}
