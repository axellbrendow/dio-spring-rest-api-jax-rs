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
import br.com.axellbrendow.diospringrestapijaxrs.controller.response.SoldierListResponse;
import br.com.axellbrendow.diospringrestapijaxrs.controller.response.SoldierResponse;
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
    public ResponseEntity<SoldierResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<SoldierListResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void create(@RequestBody Soldier soldier) {
        service.save(soldier);
    }

    @PutMapping("/{id}")
    public void update(
        @PathVariable Long id,
        @RequestBody SoldierEditRequest editRequest
    ) {
        service.update(id, editRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
