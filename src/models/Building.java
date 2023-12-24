package models;

import print.TableFormat;
import repository.Entity;

public class Building extends Entity {
    private int numberOfFloors;
    private String name;

    public Building(String name, int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
        this.name = name;

    }

    public void displayInfo() {
        TableFormat tableFormat = new TableFormat();
        tableFormat.setTitle("Bina");
        tableFormat.appendCell("ID", getId());
        tableFormat.appendCell("Isim", name);
        tableFormat.appendCell("Kat Sayisi", Integer.toString(numberOfFloors));
        System.out.print(tableFormat.createTable());

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }
}
