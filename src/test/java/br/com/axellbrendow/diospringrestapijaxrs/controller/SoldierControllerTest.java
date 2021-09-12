package br.com.axellbrendow.diospringrestapijaxrs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;

import br.com.axellbrendow.diospringrestapijaxrs.controller.request.SoldierEditRequest;
import br.com.axellbrendow.diospringrestapijaxrs.controller.response.SoldierResponse;
import br.com.axellbrendow.diospringrestapijaxrs.dto.Soldier;
import br.com.axellbrendow.diospringrestapijaxrs.enums.Race;
import br.com.axellbrendow.diospringrestapijaxrs.service.SoldierService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = SoldierController.class)
public class SoldierControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Spy
    private ObjectMapper mapper;

    @MockBean
    private SoldierService service;

    @Test
    void itShouldGetById() throws Exception {
        var soldier = new SoldierResponse();
        soldier.setName("soldier");
        soldier.setRace(Race.ELF);
        soldier.setWeapon("Arrow and bow");
        soldier.setStatus("active");

        when(service.findById(1L)).thenReturn(soldier);

        mockMvc.perform(
            get("/v1/soldier/1")
                .header("Authorization", "strongpass")
        ).andExpect(status().isOk())
        .andExpect(jsonPath("$.id", nullValue()))
        .andExpect(jsonPath("$.name", is(soldier.getName())))
        .andExpect(jsonPath("$.race", is(soldier.getRace().toString())))
        .andExpect(jsonPath("$.weapon", is(soldier.getWeapon())))
        .andExpect(jsonPath("$.status", is(soldier.getStatus())));
    }

    @Test
    void itShouldNotGetByIdIfNotLoggedIn() throws Exception {
        var soldier = new SoldierResponse();
        soldier.setName("soldier");
        soldier.setRace(Race.ELF);
        soldier.setWeapon("Arrow and bow");
        soldier.setStatus("active");

        when(service.findById(1L)).thenReturn(soldier);

        mockMvc.perform(
            get("/v1/soldier/1")
        ).andExpect(status().isUnauthorized());
    }

    @Test
    void itShouldList() throws Exception {
        when(service.findAll()).thenReturn(CollectionModel.of(new ArrayList()));

        mockMvc.perform(
            get("/v1/soldier")
                .header("Authorization", "strongpass")
        ).andExpect(status().isOk());
    }

    @Test
    void itShouldNotListIfNotLoggedIn() throws Exception {
        when(service.findAll()).thenReturn(CollectionModel.of(new ArrayList()));

        mockMvc.perform(
            get("/v1/soldier")
        ).andExpect(status().isUnauthorized());
    }

    @Test
    void itShouldCreate() throws Exception {
        var soldier = new Soldier();
        soldier.setName("soldier");
        soldier.setRace(Race.ELF);
        soldier.setWeapon("Arrow and bow");
        soldier.setStatus("active");

        doNothing().when(service).save(any());

        mockMvc.perform(
            post("/v1/soldier")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(soldier))
                .header("Authorization", "strongpass")
        ).andExpect(status().isCreated());
    }

    @Test
    void itShouldNotCreateIfNotLoggedIn() throws Exception {
        var soldier = new Soldier();
        soldier.setName("soldier");
        soldier.setRace(Race.ELF);
        soldier.setWeapon("Arrow and bow");
        soldier.setStatus("active");

        doNothing().when(service).save(any());

        mockMvc.perform(
            post("/v1/soldier")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(soldier))
        ).andExpect(status().isUnauthorized());
    }

    @Test
    void itShouldUpdate() throws Exception {
        var soldier = new SoldierEditRequest();
        soldier.setName("soldier");
        soldier.setRace(Race.ELF);
        soldier.setWeapon("Arrow and bow");
        soldier.setStatus("active");

        doNothing().when(service).update(1L, soldier);

        mockMvc.perform(
            put("/v1/soldier/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(soldier))
                .header("Authorization", "strongpass")
        ).andExpect(status().isOk());
    }

    @Test
    void itShouldNotUpdateIfNotLoggedIn() throws Exception {
        var soldier = new SoldierEditRequest();
        soldier.setName("soldier");
        soldier.setRace(Race.ELF);
        soldier.setWeapon("Arrow and bow");
        soldier.setStatus("active");

        doNothing().when(service).update(1L, soldier);

        mockMvc.perform(
            put("/v1/soldier/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(soldier))
        ).andExpect(status().isUnauthorized());
    }

    @Test
    void itShouldDelete() throws Exception {
        doNothing().when(service).delete(1L);

        mockMvc.perform(
            delete("/v1/soldier/1")
                .header("Authorization", "strongpass")
        ).andExpect(status().isNoContent());
    }

    @Test
    void itShouldNotDeleteIfNotLoggedIn() throws Exception {
        doNothing().when(service).delete(1L);

        mockMvc.perform(
            delete("/v1/soldier/1")
        ).andExpect(status().isUnauthorized());
    }
}
