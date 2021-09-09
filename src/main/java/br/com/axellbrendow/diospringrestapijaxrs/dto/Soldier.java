package br.com.axellbrendow.diospringrestapijaxrs.dto;

public class Soldier {
    private String name;
    private String race;
    private String weapon;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRace() {
        return race;
    }
    public void setRace(String race) {
        this.race = race;
    }
    public String getWeapon() {
        return weapon;
    }
    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }
}
