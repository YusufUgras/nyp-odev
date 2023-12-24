package models;

import print.TableFormat;
import repository.Entity;

public class Occupant extends Entity {
    private String name;
    private String surname;

    private Landlord landlord;

    public Occupant(String name, String surname, Landlord landlord) {
        this.name = name;
        this.surname = surname;
        this.landlord = landlord;
    }

    public void displayInfo() {
        TableFormat formatSelf = new TableFormat();
        formatSelf.setTitle("Kiraci");
        formatSelf.appendCell("Isim", name);
        formatSelf.appendCell("Soyisim", surname);
        formatSelf.appendCell("Ev Sahibi ID", landlord.getId());
        formatSelf.appendCell("Ev Sahibi", landlord.getName() + " " + landlord.getSurname());
        System.out.print(formatSelf.createTable());

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Landlord getLandlord() {
        return landlord;
    }

    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
    }

}