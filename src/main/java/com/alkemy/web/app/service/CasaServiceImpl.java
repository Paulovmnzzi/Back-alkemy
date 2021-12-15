package com.alkemy.web.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.web.app.entity.Casa;
import com.alkemy.web.app.entity.Reino;
import com.alkemy.web.app.repository.CasaRepository;
import com.alkemy.web.app.repository.ReinoRepository;

@Service
public class CasaServiceImpl {

	@Autowired
	private CasaRepository cr;
	
	@Autowired
	private ReinoRepository rr;
	
	public List<Casa> obtenerCasas(){
		return cr.findAll();
	}
	
	public Casa findCasaByNombre(String nombre) {
		return cr.findByNombre(nombre);
	}
	
	public Casa save(Casa casa, String nombreReino) {
		Reino reino = rr.findByNombre(nombreReino);
		if(casa != null && reino != null) {
			casa.setReino(reino);
			return cr.save(casa);
		}
		return null;
	}
	
}
