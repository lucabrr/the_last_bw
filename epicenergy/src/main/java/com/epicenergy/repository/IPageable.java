package com.epicenergy.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.epicenergy.entity.User;

public interface IPageable extends PagingAndSortingRepository<User, Long> {

    // Nome
    @Query("SELECT u FROM User u ORDER BY u.name ASC")
    public Page<User> findAllOrderByName(Pageable pageable);

    // Fatturato annuale
    @Query("SELECT u FROM User u ORDER BY u.fatturatoAnnuale DESC")
    public Page<User> sortByFatturatoAnnuale(Pageable pageable);

    // Data di inserimento
    @Query("SELECT u FROM User u ORDER BY u.dataInserimento DESC")
    public Page<User> sortByDataInserimento(Pageable pageable);

    // Data di ultimo contatto
    @Query("SELECT u FROM User u ORDER BY u.dataUltimoContatto DESC")
    public Page<User> sortByDataUltimoContatto(Pageable pageable);

    // Provincia della sede legale.
    @Query("SELECT u FROM User u ORDER BY u.indirizzo.comune.provincia ASC")
    public Page<User> sortByIndirizzo(Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.fatturatoAnnuale BETWEEN :fatturato AND :fatturato2")
    public Page<User> findByFatturatoAnnuale(Double fatturato, Double fatturato2, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.dataInserimento = :data")
    public Page<User> findByDataInserimento(LocalDate data, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.dataUltimoContatto = :data")
    public Page<User> findByDataUltimoContatto(LocalDate data, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.name LIKE %:nome%")
    public Page<User> findByNameContaining(String nome, Pageable pageable);

}
