package br.com.axellbrendow.diospringrestapijaxrs.controller.response;

import org.springframework.hateoas.RepresentationModel;

import br.com.axellbrendow.diospringrestapijaxrs.enums.Race;

public class SoldierResponse extends RepresentationModel<SoldierResponse> {
    private Long id;
    private String name;
    private Race race;
    private String weapon;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
