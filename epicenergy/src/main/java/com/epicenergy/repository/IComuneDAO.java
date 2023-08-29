package com.epicenergy.repository;

import org.springframework.data.repository.CrudRepository;

import com.epicenergy.entity.Comune;



public interface IComuneDAO extends CrudRepository<Comune, Long> {

}
