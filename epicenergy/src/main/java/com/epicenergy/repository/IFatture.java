package com.epicenergy.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.epicenergy.entity.Fatture;
import com.epicenergy.entity.User;
import com.epicenergy.enums.StatoFattura;

public interface IFatture extends CrudRepository<Fatture, Long> {

    public List<Fatture> findByUser(User user);

    public List<Fatture> findByStato(StatoFattura stato);

    public List<Fatture> findByDataBetween(LocalDate start, LocalDate end);

    public List<Fatture> findByImportoBetween(BigDecimal start, BigDecimal end);

}
