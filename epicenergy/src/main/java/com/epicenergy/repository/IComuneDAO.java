package com.epicenergy.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.epicenergy.entity.Comune;

public interface IComuneDAO extends CrudRepository<Comune, Long> {

    @Query("SELECT c FROM Comune c WHERE c.nome = :nome AND c.provincia.nome = :provName")
    public Comune findByNomeAndProvName(String nome, String provName);
}
