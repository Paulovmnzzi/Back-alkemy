package com.alkemy.web.app.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

import com.alkemy.web.app.entity.Casa;
import com.alkemy.web.app.service.CasaServiceImpl;

@Controller
@RequestMapping("/casas")
public class CasasController {

	@Autowired
	private CasaServiceImpl cs;
	
	public final static Log LOG = LogFactory.getLog(CasasController.class);

	@GetMapping("/listar")
	public ResponseEntity<List<Casa>> obtenerCasas() {

		return new ResponseEntity<List<Casa>>(cs.obtenerCasas(), HttpStatus.OK);

	}

	@GetMapping("/{nombre}")
	public ResponseEntity<Casa> obtenerCasaPorNombre(@PathVariable(name = "nombre", required = true) String nombre){
		if(nombre != null) {
			Casa casa = cs.findCasaByNombre(nombre);
				if(casa == null) {
					return new ResponseEntity<Casa>(HttpStatus.NOT_FOUND);
				}else {
					return new ResponseEntity<Casa>(casa, HttpStatus.OK);
				}
		}
		return new ResponseEntity<Casa>(HttpStatus.BAD_REQUEST);
		
	}
	
	@GetMapping("listar/{reino}")
	public ResponseEntity<List<Casa>> listarPorReino(@PathVariable String reino){
		List<Casa> casas = cs.findByReino(reino);
		if(casas != null) {
			return new ResponseEntity<List<Casa>>(casas, HttpStatus.OK);
		}
		return new ResponseEntity<List<Casa>>(HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping("/agregar/{reino_id}")
	public ResponseEntity<Casa> agregarCasa(@RequestBody Casa casa, @PathVariable Integer reino_id){
		
		if(casa != null && reino_id != null) {
			Casa newCasa = cs.save(casa, reino_id);
			if(newCasa != null) {
				return new ResponseEntity<Casa>(newCasa, HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<Casa>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/editar")
	public ResponseEntity<Casa> editarCasa(@RequestBody Casa casa){
		if(casa != null) {
			Casa casaEditada = cs.editarCasa(casa);
			if(casaEditada != null) {
				return new ResponseEntity<Casa>(casaEditada, HttpStatus.OK);
			}
			return new ResponseEntity<Casa>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Casa>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/borrar/{nombre}")
	public ResponseEntity<Casa> borrarCasa(@PathVariable String nombre){
		if(nombre != null) {
			Casa casa = cs.removeCasa(nombre);
			if ( casa != null ) {
				return new ResponseEntity<Casa>(casa, HttpStatus.OK);
			}
			return new ResponseEntity<Casa>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Casa>(HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	
	
}
