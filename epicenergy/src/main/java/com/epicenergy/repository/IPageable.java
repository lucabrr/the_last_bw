package com.epicenergy.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.epicenergy.entity.User;

public interface IPageable extends PagingAndSortingRepository<User, Long> {

    // // Nome

    // @Query("SELECT u FROM User u ORDER BY u.name ASC")
    // public List<User> findAllOrderByName(Pageable pageable);

    // // Fatturato annuale

    // @Query("SELECT u FROM User u ORDER BY u.fatturatoAnnuale DESC")
    // public List<User> sortByFatturatoAnnuale(Pageable pageable);

    // // Data di inserimento

    // @Query("SELECT u FROM User u ORDER BY u.dataInserimento DESC")
    // public List<User> sortByDataInserimento(Pageable pageable);

    // // Data di ultimo contatto

    // @Query("SELECT u FROM User u ORDER BY u.dataUltimoContatto DESC")
    // public List<User> sortByDataUltimoContatto(Pageable pageable);

    // // Provincia della sede legale.

    // @Query("SELECT u FROM User u ORDER BY u.indirizzo.comune.provincia ASC")
    // public List<User> sortByIndirizzo(Pageable pageable);

    // @Query("SELECT u FROM User u WHERE u.fatturatoAnnuale = :fatturato")
    // public List<User> findByFatturatoAnnuale(Double fatturato, Pageable
    // pageable);

    // @Query("SELECT u FROM User u WHERE u.dataInserimento = :data")
    // public List<User> findByDataInserimento(LocalDate data, Pageable pageable);

    // @Query("SELECT u FROM User u WHERE u.dataUltimoContatto = :data")
    // public List<User> findByDataUltimoContatto(LocalDate data, Pageable
    // pageable);

    // public List<User> findByNameContaining(String nome, Pageable pageable);

}
