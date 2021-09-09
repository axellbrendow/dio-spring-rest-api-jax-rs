package br.com.axellbrendow.diospringrestapijaxrs.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.axellbrendow.diospringrestapijaxrs.controller.request.SoldierEditRequest;
import br.com.axellbrendow.diospringrestapijaxrs.dto.Soldier;
import br.com.axellbrendow.diospringrestapijaxrs.service.SoldierService;

@RestController
@RequestMapping("/v1/soldier")
public class SoldierController {
    private SoldierService service;

    public SoldierController(SoldierService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Soldier> get() {
        return ResponseEntity.ok(service.get());
    }

    @GetMapping
    public ResponseEntity<List<Soldier>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Soldier> create(@RequestBody Soldier soldier) {
        return ResponseEntity.ok(service.create(soldier));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Soldier> update(
        @PathVariable String id,
        @RequestBody SoldierEditRequest editRequest
    ) {
        service.update(id, editRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
