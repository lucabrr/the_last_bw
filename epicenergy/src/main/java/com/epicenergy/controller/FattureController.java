package com.epicenergy.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epicenergy.entity.Fatture;
import com.epicenergy.enums.StatoFattura;
import com.epicenergy.exception.MyAPIException;
import com.epicenergy.repository.IFatture;
import com.epicenergy.service.FattureService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class FattureController {

    @Autowired
    IFatture frepo;

    @Autowired
    FattureService fs;

    @GetMapping("/fatture/{id}")
    public ResponseEntity<Fatture> getFattura(@PathVariable Long id) {
        return ResponseEntity.ok(fs.getFattureById(id));
    }

    @GetMapping("/fatture")
    public ResponseEntity<Iterable<Fatture>> getAllFatture() {
        return ResponseEntity.ok(fs.getAllFatture());
    }

    @DeleteMapping("/fatture/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (fs.deleteFattura(id)) {
            return ResponseEntity.ok("Fattura cancellata");
        } else {
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "Fattura non trovata");
        }
    }

    @PutMapping("/fatture/{id}")
    public ResponseEntity<?> updateFattura(@PathVariable Long id, @RequestBody Fatture f) {
        Fatture fattura = fs.updateFattura(id, f);
        return ResponseEntity.ok(fattura);
    }

    @GetMapping("/fatture/stato/{stato}")
    public ResponseEntity<?> getByStato(@PathVariable StatoFattura stato) {
        return ResponseEntity.ok(fs.getByStato(stato));
    }

    @GetMapping("/fatture/byuser/{id}")
    public ResponseEntity<List<Fatture>> getByUser(@PathVariable Long id) {
        return ResponseEntity.ok(fs.getByUser(id));
    }

    @GetMapping("/fatture/bydata")
    public ResponseEntity<?> getByDataBetween(@RequestParam String start, String end) {
        return ResponseEntity.ok(fs.getByDataBetween(LocalDate.parse(start), LocalDate.parse(end)));
    }

    @GetMapping("/fatture/byimporto")
    public ResponseEntity<?> getByImportoBetween(@RequestParam BigDecimal start, @RequestParam BigDecimal end) {
        return ResponseEntity.ok(fs.getByImportoBetween(start, end));
    }

}
