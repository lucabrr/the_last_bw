package com.epicenergy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epicenergy.entity.User;
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
}
