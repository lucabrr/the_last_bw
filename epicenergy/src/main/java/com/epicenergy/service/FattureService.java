package com.epicenergy.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.epicenergy.entity.Fatture;
import com.epicenergy.enums.StatoFattura;
import com.epicenergy.exception.MyAPIException;
import com.epicenergy.repository.IFatture;
import com.epicenergy.repository.UserRepository;

@Service
public class FattureService {

    @Autowired
    IFatture fattureRepo;
    @Autowired
    UserRepository userRepo;

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

    public Iterable<Fatture> getAllFatture() {
        return fattureRepo.findAll();
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

    public List<Fatture> getByUser(Long id) {
        return this.fattureRepo.findByUser(userRepo.findById(id).get());
    }

    public List<Fatture> getByStato(StatoFattura stato) {
        return this.fattureRepo.findByStato(stato);
    }

    public List<Fatture> getByDataBetween(LocalDate start, LocalDate end) {
        return this.fattureRepo.findByDataBetween(start, end);
    }

    public List<Fatture> getByImportoBetween(BigDecimal start, BigDecimal end) {
        return this.fattureRepo.findByImportoBetween(start, end);
    }

}
