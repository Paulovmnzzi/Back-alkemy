package com.alkemy.web.app.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.web.app.entity.Casa;

@Repository
public interface CasaRepository extends JpaRepository<Casa, Serializable>{

	public Casa findByNombre(String nombre);
	
}
