package com.epicenergy.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.epicenergy.entity.User;
import com.epicenergy.exception.MyAPIException;
import com.epicenergy.repository.IPageable;
import com.epicenergy.repository.UserRepository;

@Service
public class ClientiService {
    @Autowired
    UserRepository clienti_repo;
    @Autowired
    IPageable page_repo;

    public User getById(Long id) {
        if (clienti_repo.existsById(id)) {
            return this.clienti_repo.findById(id).get();
        } else {
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "Utente non trovato!");
        }
    }

    public Page<User> getClientiPaginati(Pageable pageable) {
        return this.page_repo.findAll(pageable);
    }

    public Page<User> sortByFatturato(Pageable pageable) {
        return this.page_repo.sortByFatturatoAnnuale(pageable);
    }

    public Page<User> orderByName(Pageable pageable) {
        return this.page_repo.findAllOrderByName(pageable);
    }

    public Page<User> sortByDataInserimento(Pageable pageable) {
        return this.page_repo.sortByDataInserimento(pageable);
    }

    public Page<User> sortByUltimoContatto(Pageable pageable) {
        return this.page_repo.sortByDataUltimoContatto(pageable);
    }

    public Page<User> sortByIndirizzo(Pageable pageable) {
        return this.page_repo.sortByIndirizzo(pageable);
    }

    public Page<User> findByFatturatoAnnuale(Double fatturato, Double fatturato2, Pageable pageable) {
        return this.page_repo.findByFatturatoAnnuale(fatturato, fatturato2, pageable);
    }

    public Page<User> findByDataInserimento(LocalDate data, Pageable pageable) {
        return this.page_repo.findByDataInserimento(data, pageable);
    }

    public Page<User> findByDataUltimoContatto(LocalDate data, Pageable pageable) {
        return this.page_repo.findByDataUltimoContatto(data, pageable);
    }

    public Page<User> findByNameContaining(String nome, Pageable pageable) {
        return this.page_repo.findByNameContaining(nome.toLowerCase(), pageable);
    }

    public User updateUser(Long id, User u) {
        return this.clienti_repo.save(u);
    }

    public boolean deleteUser(Long id) {
        if (this.clienti_repo.existsById(id)) {
            this.clienti_repo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
