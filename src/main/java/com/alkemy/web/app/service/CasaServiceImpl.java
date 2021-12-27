package com.alkemy.web.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	public List<Casa> obtenerCasas() {
		return cr.findAll();
	}

	public Casa findCasaByNombre(String nombre) {
		return cr.findByNombre(nombre);
	}

	public Casa save(Casa casa, Integer nombreReino) {
		Optional<Reino> reinoOp = rr.findById(nombreReino);

		if (casa != null && reinoOp.isPresent()) {
			Reino reino = reinoOp.get();
			casa.setReino(reino);
			return cr.save(casa);
		}
		return null;
	}

	public List<Casa> findByReino(String nombreReino) {
		if (nombreReino != null) {
			Reino reino = rr.findByNombre(nombreReino);
			List<Casa> casas = new ArrayList<Casa>();
			if (reino != null) {
				for (Casa casa : reino.getCasas()) {
					casas.add(new Casa(casa.getNombre(), casa.getImagen(), casa.getHistoria(), casa.getPersonajes()));
				}
				if (casas != null) {
					return casas;
				}
			}
		}
		return null;
	}

	public Casa editarCasa(Casa casa) {
		if (casa != null) {
			Casa oldCasa = cr.findByNombre(casa.getNombre());
			Casa newCasa = casa;
			newCasa.setReino(oldCasa.getReino());
			return cr.save(newCasa);
		}
		return null;
	}

	public Casa removeCasa(String nombreCasa) {
		Casa casa = cr.findByNombre(nombreCasa);
		if (casa != null) {
			cr.delete(casa);
			return casa;
		}
		return null;
	}

	public List<Casa> findByReinoId(Integer reinoId) {
		
		if(reinoId != null) {
			Optional<Reino> reinoOp = rr.findById(reinoId);
			List<Casa> casas = new ArrayList<Casa>();
			if(reinoOp.isPresent()) {
				Reino reino = reinoOp.get();
				for(Casa casa : reino.getCasas()) {
					casas.add(new Casa(casa.getNombre(), casa.getImagen(), casa.getHistoria(), casa.getPersonajes()));
				}
				if(casas != null && !casas.isEmpty()) {
					return casas;
				}
			}
		}
		return null;
	}
		
		
		
		
		
		


}
