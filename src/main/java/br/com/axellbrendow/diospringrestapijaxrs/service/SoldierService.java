package br.com.axellbrendow.diospringrestapijaxrs.service;

import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import br.com.axellbrendow.diospringrestapijaxrs.controller.request.SoldierEditRequest;
import br.com.axellbrendow.diospringrestapijaxrs.controller.response.SoldierListResponse;
import br.com.axellbrendow.diospringrestapijaxrs.controller.response.SoldierResponse;
import br.com.axellbrendow.diospringrestapijaxrs.dto.Soldier;
import br.com.axellbrendow.diospringrestapijaxrs.entity.SoldierEntity;
import br.com.axellbrendow.diospringrestapijaxrs.exception.NotFoundException;
import br.com.axellbrendow.diospringrestapijaxrs.repository.SoldierRepository;
import br.com.axellbrendow.diospringrestapijaxrs.resource.SoldierResource;

@Service
public class SoldierService {
    private SoldierRepository repository;
    private ObjectMapper mapper;
    private SoldierResource resource;

    public SoldierService(SoldierRepository repository, ObjectMapper mapper, SoldierResource resource) {
        this.repository = repository;
        this.mapper = mapper;
        this.resource = resource;
    }

    public CollectionModel<SoldierListResponse> findAll() {
        var soldiers = repository.findAll();
        var dtos = soldiers.stream()
            .map(it -> resource.createFindAllLinks(it))
            .collect(Collectors.toList());
        return CollectionModel.of(dtos);
    }

    public SoldierResponse findById(Long id) {
        var soldier = repository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return resource.createDetailsLinks(soldier);
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
