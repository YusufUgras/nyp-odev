package models;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import print.TableFormat;
import repository.Entity;

// Ev sahibi varligi
public class Landlord extends Entity {
    private String name;
    private String surname;
    private List<Flat> flats = new ArrayList<>();

    public Landlord(String name, String surname, Flat flat) {
        this.name = name;
        this.surname = surname;
        this.flats.add(flat);
    }

    // Bilgilerini tablo seklinde yazdiran metot
    public void displayInfo() {
        TableFormat formatSelf = new TableFormat();
        formatSelf.appendCell("ID", this.getId());
        formatSelf.setTitle("Ev Sahibi");
        formatSelf.appendCell("Isim", name);
        formatSelf.appendCell("Soyisim", surname);
        System.out.print(formatSelf.createTable());

        if (!flats.isEmpty()) {
            TableFormat formatFlats = new TableFormat();
            formatFlats.setTitle(name + " Daireleri");
            for (Flat flat : flats) {
                formatFlats.appendCell("ID: " + flat.getId(), "No:  " + flat.getApartmentNumber());
            }
            System.out.print(formatFlats.createTable());
        }

    }

    public void addApartment(Flat flat) {
        this.flats.add(flat);
    }

    public void removeApartment(Predicate<Flat> predicate) {
        this.flats.removeIf(predicate);
    }

    public List<Flat> getApartments() {
        return this.flats;
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
}
