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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alkemy.web.app.entity.Reino;
import com.alkemy.web.app.service.ReinoServiceImpl;

@Controller
@RequestMapping("/reinos")
public class ReinoController {
	
	@Autowired
	private ReinoServiceImpl rs;

	@GetMapping("/listar")
	public ResponseEntity<List<Reino>> obtenerReinos(){
		List<Reino> reinos = rs.findAll();
		if(reinos != null) {
			return new ResponseEntity<List<Reino>>(reinos, HttpStatus.OK);
		}
		return new ResponseEntity<List<Reino>>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/cargar/{id}")
	public ResponseEntity<Reino> cargaParaEditar(@PathVariable Integer id){
		Reino reino = rs.cargarParaeditarReino(id);
		if( reino != null ) {
			return new ResponseEntity<Reino>(reino, HttpStatus.OK);
		}
		return new ResponseEntity<Reino>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<Reino> agregarReino(@RequestBody Reino reino){
		if(reino != null) {
			Reino newReino = rs.nuevoReino(reino);
			if(newReino != null) {
				return new ResponseEntity<Reino>(newReino, HttpStatus.CREATED);
			}
			return new ResponseEntity<Reino>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Reino>(HttpStatus.BAD_REQUEST);
	}
	
	
	@PutMapping("/editar")
	public ResponseEntity<Reino> editarReino(Reino reino) {
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
	public ResponseEntity<Reino> eliminarReino(@PathVariable Integer id){
		if(id != null) {
			Reino reino = rs.eliminarReino(id);
			if( reino != null) {
				return new ResponseEntity<Reino>(reino, HttpStatus.OK);
			}
			return new ResponseEntity<Reino>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Reino>(HttpStatus.BAD_REQUEST);
	}
	
	
	
	
}
