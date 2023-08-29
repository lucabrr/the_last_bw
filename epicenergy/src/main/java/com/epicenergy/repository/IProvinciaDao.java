package com.epicenergy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epicenergy.entity.Provincia;


@Repository
public interface IProvinciaDao extends CrudRepository<Provincia, Long>{
	
	public Provincia findByNome(String nome);
}
