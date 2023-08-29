package com.epicenergy.repository;

import org.springframework.data.repository.CrudRepository;
import com.epicenergy.entity.Provincia;

public interface IProvinciaDao extends CrudRepository<Provincia, Long> {

	public Provincia findByNome(String nome);

	public Provincia findBySigla(String sigla);
}
