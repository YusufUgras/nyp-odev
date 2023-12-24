package models;

import print.TableFormat;
import repository.Entity;

public class Flat extends Entity implements Comparable<Flat> {

    private int apartmentNumber;

    public Flat(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public void displayInfo() {
        TableFormat tableFormat = new TableFormat();
        tableFormat.setTitle("Daire");
        tableFormat.appendCell("ID ", getId());
        tableFormat.appendCell("No ", Integer.toString(apartmentNumber));
        System.out.println(tableFormat.createTable());

    }

    @Override
    public int compareTo(Flat arg0) {
        return this.getApartmentNumber() - arg0.getApartmentNumber();

    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

}
