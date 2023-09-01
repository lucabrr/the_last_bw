package com.epicenergy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.epicenergy.entity.Fatture;
import com.epicenergy.entity.User;
import com.epicenergy.exception.MyAPIException;
import com.epicenergy.repository.IFatture;

public class FattureService {

    @Autowired
    IFatture fattureRepo;

    public void salvaFattura(Fatture f) {
        try {
            fattureRepo.save(f);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Fatture getFattureById(Long id) {
        if (fattureRepo.existsById(id)) {
            return this.fattureRepo.findById(id).get();
        } else {
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "Fattura non trovata!");
        }
    }

    public boolean deleteFattura(Long id) {
        if (this.fattureRepo.existsById(id)) {
            this.fattureRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Fatture updateFattura(Long id, Fatture f) {
        return this.fattureRepo.save(f);
    }

}
