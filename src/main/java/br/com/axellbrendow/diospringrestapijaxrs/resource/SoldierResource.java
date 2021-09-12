package br.com.axellbrendow.diospringrestapijaxrs.resource;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.com.axellbrendow.diospringrestapijaxrs.controller.SoldierController;
import br.com.axellbrendow.diospringrestapijaxrs.controller.response.SoldierListResponse;
import br.com.axellbrendow.diospringrestapijaxrs.controller.response.SoldierResponse;
import br.com.axellbrendow.diospringrestapijaxrs.entity.SoldierEntity;

@Component
public class SoldierResource {
    private ObjectMapper mapper;

    public SoldierResource(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public SoldierListResponse createFindAllLinks(SoldierEntity entity) {
        var res = mapper.convertValue(entity, SoldierListResponse.class);

        res.add(
            WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(SoldierController.class).findById(entity.getId())
            ).withSelfRel()
        );

        res.add(
            WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(SoldierController.class).delete(entity.getId())
            ).withRel("delete").withTitle("Delete soldier").withType("delete")
        );

        return res;
    }

    public SoldierResponse createDetailsLinks(SoldierEntity entity) {
        var res = mapper.convertValue(entity, SoldierResponse.class);

        res.add(
            WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(SoldierController.class).getAll()
            ).withRel("list")
        );

        res.add(
            WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(SoldierController.class).delete(entity.getId())
            ).withRel("delete").withTitle("Delete soldier").withType("delete")
        );

        return res;
    }
}
