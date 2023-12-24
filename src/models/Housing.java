package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import print.TableFormat;
import repository.Entity;

public class Housing extends Entity {
    private String address;
    public List<Building> buildings = new ArrayList<>();
    public List<Flat> flats = new ArrayList<>();

    public Housing(String address) {
        this.address = address;

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void displayInfo() {
        TableFormat formatSelf = new TableFormat();
        formatSelf.setTitle("Konut");
        formatSelf.appendCell("ID", this.getId());
        formatSelf.appendCell("Adres", address);
        System.out.print(formatSelf.createTable());
        if (!buildings.isEmpty()) {
            TableFormat formatBuilding = new TableFormat();
            formatBuilding.setTitle("Konuttaki Binalar");
            for (Building building : buildings) {
                formatBuilding.appendCell("ID : " + building.getId(), "Isim :" + building.getName());
            }
            System.out.print(formatBuilding.createTable());
        }
        if (!flats.isEmpty()) {
            TableFormat formatFlat = new TableFormat();
            formatFlat.setTitle("Konuttaki Daireler");
            Collections.sort(flats);
            for (Flat flat : flats) {
                formatFlat.appendCell("ID : " + flat.getId(), "No :" + flat.getApartmentNumber());
            }
            System.out.print(formatFlat.createTable());
        }

    }

}
