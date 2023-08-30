package com.epicenergy.service;

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
}
