package com.epicenergy.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.epicenergy.entity.User;
import com.epicenergy.exception.MyAPIException;
import com.epicenergy.service.ClientiService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class ClientiController {

    @Autowired
    ClientiService svc;

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getSingleUser(@PathVariable Long id) {
        return ResponseEntity.ok(svc.getById(id));
    }

    @GetMapping("/users")
    public ResponseEntity<Page<User>> getUsersPaginati(Pageable pageable) {
        return ResponseEntity.ok(svc.getClientiPaginati(pageable));
    }

    @GetMapping("/sort/fatturato")
    public ResponseEntity<Page<User>> sortByFatturato(Pageable pageable) {
        return ResponseEntity.ok(svc.sortByFatturato(pageable));
    }

    @GetMapping("/sort/nome")
    public ResponseEntity<Page<User>> orderByName(Pageable pageable) {
        return ResponseEntity.ok(svc.orderByName(pageable));
    }

    @GetMapping("/sort/datainserimento")
    public ResponseEntity<Page<User>> srtByDataInserimento(Pageable pageable) {
        return ResponseEntity.ok(svc.sortByDataInserimento(pageable));
    }

    @GetMapping("/sort/ultimocontatto")
    public ResponseEntity<Page<User>> sortByUltimoContatto(Pageable pageable) {
        return ResponseEntity.ok(svc.sortByUltimoContatto(pageable));
    }

    @GetMapping("/sort/indirizzo")
    public ResponseEntity<Page<User>> sortByIndirizzo(Pageable pageable) {
        return ResponseEntity.ok(svc.sortByIndirizzo(pageable));
    }

    @GetMapping("/findfatturato/{fatturato}")
    public ResponseEntity<Page<User>> findByFatturatoAnnuale(@RequestParam Double fatturato,
            @RequestParam Double fatturato2, Pageable pageable) {
        return ResponseEntity.ok(svc.findByFatturatoAnnuale(fatturato, fatturato2, pageable));
    }

    @GetMapping("/findbydata/{data}")
    public ResponseEntity<Page<User>> findByDataInserimento(@RequestParam String data, Pageable pageable) {
        return ResponseEntity.ok(svc.findByDataInserimento(LocalDate.parse(data), pageable));
    }

    @GetMapping("/findname/containing")
    public ResponseEntity<Page<User>> findByNameContaining(@RequestParam String name, Pageable pageable) {
        return ResponseEntity.ok(svc.findByNameContaining(name, pageable));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User u) {
        User user = svc.updateUser(id, u);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (svc.deleteUser(id)) {
            return ResponseEntity.ok("User deleted succesfully");
        } else {
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "User not found");
        }
    }

}
