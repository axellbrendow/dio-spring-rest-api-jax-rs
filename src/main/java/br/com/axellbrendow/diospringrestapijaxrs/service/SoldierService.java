package br.com.axellbrendow.diospringrestapijaxrs.service;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import br.com.axellbrendow.diospringrestapijaxrs.controller.request.SoldierEditRequest;
import br.com.axellbrendow.diospringrestapijaxrs.controller.response.SoldierResponse;
import br.com.axellbrendow.diospringrestapijaxrs.dto.Soldier;
import br.com.axellbrendow.diospringrestapijaxrs.entity.SoldierEntity;
import br.com.axellbrendow.diospringrestapijaxrs.exception.NotFoundException;
import br.com.axellbrendow.diospringrestapijaxrs.repository.SoldierRepository;

@Service
public class SoldierService {
    private SoldierRepository repository;
    private ObjectMapper mapper;

    public SoldierService(SoldierRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Soldier> findAll() {
        var soldiers = repository.findAll();
        var dtos = soldiers.stream()
            .map(it -> mapper.convertValue(it, Soldier.class))
            .collect(Collectors.toList());
        return dtos;
    }

    public SoldierResponse findById(Long id) {
        var soldier = repository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return mapper.convertValue(soldier, SoldierResponse.class);
    }

    public void save(Soldier soldier) {
        var entity = mapper.convertValue(soldier, SoldierEntity.class);
        repository.save(entity);
    }

    public void delete(Long id) {
        repository.delete(repository.findById(id).orElseThrow(() -> new NotFoundException(id)));
    }

    public void update(Long id, SoldierEditRequest editRequest) {
        var soldier = mapper.convertValue(editRequest, Soldier.class);
        soldier.setId(id);
        save(soldier);
    }
}
