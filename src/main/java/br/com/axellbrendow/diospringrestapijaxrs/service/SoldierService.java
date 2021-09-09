package br.com.axellbrendow.diospringrestapijaxrs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.axellbrendow.diospringrestapijaxrs.controller.request.SoldierEditRequest;
import br.com.axellbrendow.diospringrestapijaxrs.dto.Soldier;

@Service
public class SoldierService {
    public Soldier get() {
        var soldier = new Soldier();
        soldier.setName("Seloken");
        soldier.setRace("Elf");
        soldier.setWeapon("Bow and arrow");
        return soldier;
    }

    public Soldier create(Soldier soldier) {
        return soldier;
    }

    public void update(String id, SoldierEditRequest editRequest) {
    }

    public void delete(String id) {
    }

    public List<Soldier> getAll() {
        var soldier = new Soldier();
        soldier.setName("Seloken");
        soldier.setRace("Elf");
        soldier.setWeapon("Bow and arrow");
        var list = new ArrayList<Soldier>();
        list.add(soldier);
        return list;
    }
}
