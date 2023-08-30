package com.epicenergy.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.epicenergy.entity.Comune;

public interface IComuneDAO extends CrudRepository<Comune, Long> {

    @Query("SELECT c FROM Comune c JOIN c.provincia cp WHERE c.nome = :nome AND cp.sigla = :provSigla")
    public Comune findByNomeAndProvSigla(String nome, String provSigla);

    public Comune findByNome(String nome);

    @Query("SELECT c FROM Comune c ORDER BY RANDOM() LIMIT 1")
    public Comune getRandomComune();
}
