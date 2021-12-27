package com.alkemy.web.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.web.app.entity.Reino;
import com.alkemy.web.app.repository.ReinoRepository;

@Service
public class ReinoServiceImpl {

	@Autowired
	private ReinoRepository rr;
	
	
	public List<Reino> findAll() {
		return rr.findAll();
	}
	
	public Reino findByNombre(String nombre) {
		if(nombre != null){
			Reino reino = rr.findByNombre(nombre);
			if(reino != null) {
				return reino;
			}
		}
		return null;
	}
	
	public Reino nuevoReino(Reino reino) {
		if(reino != null) {
			return rr.save(reino);
		}
		return null;
	}
	
	public Reino cargarParaeditarReino(Integer id) {
		Optional<Reino> reinodb = rr.findById(id);
		if(reinodb.isPresent()) {
			return reinodb.get();
		}
		return null;
	}
	
	public Reino eliminarReino(Integer id) {
		Optional<Reino> reinodb = rr.findById(id);
		if(reinodb.isPresent()) {
			rr.delete(reinodb.get());
			return reinodb.get();
		}
		return null;
	}
	
	
}
