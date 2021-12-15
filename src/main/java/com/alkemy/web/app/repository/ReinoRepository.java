package com.alkemy.web.app.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.web.app.entity.Reino;

@Repository
public interface ReinoRepository extends JpaRepository<Reino, Serializable> {

	public Reino findByNombre(String nombre);
	
	
	
}
