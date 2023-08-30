package com.epicenergy.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epicenergy.entity.User;

@Repository
public interface IClienti extends CrudRepository<User, Long> {

    // Nome

    @Query("SELECT u FROM User u ORDER BY u.name ASC")
    public List<User> findAllOrderByName();

    // Fatturato annuale

    @Query("SELECT u FROM User u ORDER BY u.fatturatoAnnuale DESC")
    public List<User> sortByFatturatoAnnuale();

    // Data di inserimento

    @Query("SELECT u FROM User u ORDER BY u.dataInserimento DESC")
    public List<User> sortByDataInserimento();

    // Data di ultimo contatto

    @Query("SELECT u FROM User u ORDER BY u.dataUltimoContatto DESC")
    public List<User> sortByDataUltimoContatto();

    // Provincia della sede legale.

    @Query("SELECT u FROM User u ORDER BY u.indirizzo.comune.provincia ASC")
    public List<User> sortByIndirizzo();

    @Query("SELECT u FROM User u WHERE u.fatturatoAnnuale = :fatturato")
    public List<User> findByFatturatoAnnuale(Double fatturato);

    @Query("SELECT u FROM User u WHERE u.dataInserimento = :data")
    public List<User> findByDataInserimento(LocalDate data);

    @Query("SELECT u FROM User u WHERE u.dataUltimoContatto = :data")
    public List<User> findByDataUltimoContatto(LocalDate data);

    public List<User> findByNameContaining(String nome);

}
