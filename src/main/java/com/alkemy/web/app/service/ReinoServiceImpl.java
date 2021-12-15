package com.alkemy.web.app.service;

import java.util.List;

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
	
	
	
	
}
