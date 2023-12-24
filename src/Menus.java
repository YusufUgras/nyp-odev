import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import models.Building;
import models.Flat;
import models.Housing;
import models.Landlord;
import models.Occupant;
import print.FrameFormat;
import repository.Repository;

public class Menus {

    static Repository<Flat> flatRepo = new Repository<>();
    static Repository<Building> buildingRepo = new Repository<>();
    static Repository<Housing> housingRepo = new Repository<>();
    static Repository<Landlord> landlordRepo = new Repository<>();
    static Repository<Occupant> occupantRepo = new Repository<>();

    static Scanner scanner = new Scanner(System.in);

    public static String input(String desc) {
        System.out.print("[ " + desc + " ]" + " > ");
        return scanner.nextLine().trim();

    }

    public static void outputLine(String str) {
        System.out.println(str);
    }

    public static void mainMenu() {
        FrameFormat frameFormat = new FrameFormat();
        frameFormat.setTitle("NYP Odev - Emlak");

        frameFormat.appendLine("Asagidaki seceneklerden birini seciniz :");
        frameFormat.appendLine("");

        frameFormat.appendLine("1 Konut Islemleri");
        frameFormat.appendLine("2 Daire Islemleri");
        frameFormat.appendLine("3 Bina Islemleri");
        frameFormat.appendLine("4 Ev Sahibi Islemleri");
        frameFormat.appendLine("5 Kiraci Islemleri");
        frameFormat.appendLine("h Menuyu Yazdir");
        frameFormat.appendLine("q Uygulamadan Cik");

        String menuDescriptor = frameFormat.createFrame();
        outputLine(menuDescriptor);
        boolean run = true;
        while (run) {
            String in = input("(Ana Menu) 1 2 3 4 5 h q");

            switch (in) {
                case "1":
                    housing();
                    break;
                case "2":
                    flat();
                    break;
                case "3":
                    building();
                    break;
                case "4":
                    landlord();
                    break;
                case "5":
                    occupant();
                    break;
                case "h":
                    outputLine(menuDescriptor);
                    break;
                case "q":
                    run = false;
                    break;

                default:
                    FrameFormat errorFrame = new FrameFormat();
                    errorFrame.setTitle("Hata!");
                    errorFrame.appendLine("Gecersiz Secenek!");
                    System.out.print(errorFrame.createFrame());
                    outputLine(menuDescriptor);
                    break;
            }

        }

    }

    public static void housing() {
        FrameFormat frameFormat = new FrameFormat();
        frameFormat.setTitle("Konut Islemleri");

        frameFormat.appendLine("Asagidaki seceneklerden birini seciniz :");
        frameFormat.appendLine("");
        frameFormat.appendLine("1 Konut Ekle");
        frameFormat.appendLine("2 Konut Cikar");
        frameFormat.appendLine("3 Konutlari Listele");
        frameFormat.appendLine("4 Konuta Daire Ekle");
        frameFormat.appendLine("5 Konuta Bina Ekle");
        frameFormat.appendLine("h Menuyu Yazdir");
        frameFormat.appendLine("q Menuye Don");
        String menuDescriptor = frameFormat.createFrame();
        outputLine(menuDescriptor);
        boolean run = true;
        while (run) {
            String in = input("(Konut Menu) 1 2 3 4 5 h q");

            switch (in) {
                case "1" -> {
                    String address = input("Adres Giriniz");
                    Housing housing = new Housing(address);
                    housingRepo.add(housing);
                    break;
                }
                case "2" -> {
                    listHousing();
                    String id = input("ID");
                    Housing housing = housingRepo.getById(id);
                    for (Flat flat : housing.flats) {
                        flatRepo.remove(x -> x.getId().equals(flat.getId()));
                    }
                    for (Building building : housing.buildings) {
                        buildingRepo.remove(x -> x.getId().equals(building.getId()));
                    }
                    housingRepo.remove(x -> x.getId().equals(id));
                    break;
                }
                case "3" -> {
                    listHousing();
                    break;
                }
                case "4" -> {
                    listHousing();
                    String housingId = input("Konut ID");

                    Housing housing = housingRepo.getById(housingId);
                    if (housing == null) {
                        outputLine("Konut Bulunamadi");
                        break;
                    }
                    listFlat();
                    String flatId = input("Daire ID");
                    Flat flat = flatRepo.getById(flatId);
                    if (flat == null) {
                        outputLine("Daire Bulunamadi");
                        break;

                    }
                    housing.flats.add(flat);
                    break;
                }
                case "5" -> {
                    listHousing();
                    String housingId = input("Konut ID");
                    Housing housing = housingRepo.getById(housingId);
                    if (housing == null) {
                        outputLine("Konut Bulunamadi");
                        break;
                    }
                    listBuilding();
                    String buildingId = input("Bina ID");
                    Building building = buildingRepo.getById(buildingId);
                    if (building == null) {
                        outputLine("Bina Bulunamadi");
                        break;
                    }
                    housing.buildings.add(building);
                    break;
                }
                case "h" -> {
                    outputLine(menuDescriptor);
                    break;
                }
                case "q" -> {
                    run = false;
                    break;
                }

                default -> {
                    FrameFormat errorFrame = new FrameFormat();
                    errorFrame.setTitle("Hata!");
                    errorFrame
                            .appendLine("Gecersiz Secenek!");
                    System.out
                            .print(errorFrame.createFrame());
                    outputLine(menuDescriptor);
                    break;
                }
            }

        }

    }

    public static void listHousing() {
        List<Housing> housingList = housingRepo.get(x -> true);
        if (housingList.isEmpty())
            outputLine("Hic oge yok");
        for (Housing housing : housingList) {
            housing.displayInfo();
        }

    }

    public static void listFlat() {
        List<Flat> flatList = flatRepo.get(x -> true);
        if (flatList.isEmpty())
            outputLine("Hic oge yok");
        Collections.sort(flatRepo.entities);

        for (Flat flat : flatRepo.entities) {
            flat.displayInfo();
        }
    }

    public static void listBuilding() {
        List<Building> buildingList = buildingRepo.get(x -> true);

        if (buildingList.isEmpty())
            outputLine("Hic oge yok");
        for (Building building : buildingList) {
            building.displayInfo();
        }
    }

    public static void listLandlord() {
        List<Landlord> landlordList = landlordRepo.get(x -> true);
        if (landlordList.isEmpty()) {
            outputLine("Hic oge yok");
        }
        for (Landlord landlord : landlordList) {
            landlord.displayInfo();
        }
    }

    public static void listOccupant() {
        List<Occupant> occupantList = occupantRepo.get(x -> true);
        if (occupantList.isEmpty()) {
            outputLine("Hic oge yok");
        }
        for (Occupant occupant : occupantList) {
            occupant.displayInfo();
        }
    }

    public static void flat() {
        FrameFormat frameFormat = new FrameFormat();
        frameFormat.setTitle("Daire Islemleri");

        frameFormat.appendLine("Asagidaki seceneklerden birini seciniz :");
        frameFormat.appendLine("");
        frameFormat.appendLine("1 Daire Ekle");
        frameFormat.appendLine("2 Daire Cikar");
        frameFormat.appendLine("3 Daireleri Listele");
        frameFormat.appendLine("h Menuyu Yazdir");
        frameFormat.appendLine("q Menuye Don");
        String menuDescriptor = frameFormat.createFrame();
        outputLine(menuDescriptor);
        boolean run = true;
        while (run) {
            String in = input("(Daire Menu) 1 2 3 h q");

            switch (in) {
                case "1" -> {
                    String no = input("Kapi No Giriniz");
                    Flat flat = new Flat(Integer.parseInt(no));
                    flatRepo.add(flat);
                    break;
                }
                case "2" -> {
                    listFlat();
                    String id = input("ID");
                    Flat flat = flatRepo.getById(id);
                    if (flat == null) {
                        outputLine("Daire bulunamadi");
                        break;
                    }
                    List<Housing> housingList = housingRepo.get(x -> x.flats.contains(flat));
                    for (Housing housing : housingList) {
                        housing.flats.remove(flat);
                    }
                    flatRepo.remove(x -> x.getId().equals(id));
                    break;
                }
                case "3" -> {
                    listFlat();
                    break;
                }
                case "h" -> {
                    outputLine(menuDescriptor);
                    break;
                }
                case "q" -> {
                    run = false;
                    break;
                }

                default -> {
                    FrameFormat errorFrame = new FrameFormat();
                    errorFrame.setTitle("Hata!");
                    errorFrame
                            .appendLine("Gecersiz Secenek!");
                    System.out
                            .print(errorFrame.createFrame());
                    outputLine(menuDescriptor);
                    break;
                }
            }

        }

    }

    public static void building() {
        FrameFormat frameFormat = new FrameFormat();
        frameFormat.setTitle("Bina Islemleri");

        frameFormat.appendLine("Asagidaki seceneklerden birini seciniz :");
        frameFormat.appendLine("");
        frameFormat.appendLine("1 Bina Ekle");
        frameFormat.appendLine("2 Bina Cikar");
        frameFormat.appendLine("3 Binalari Listele");
        frameFormat.appendLine("h Menuyu Yazdir");
        frameFormat.appendLine("q Menuye Don");
        String menuDescriptor = frameFormat.createFrame();
        outputLine(menuDescriptor);
        boolean run = true;
        while (run) {
            String in = input("(Bina Menu) 1 2 3 h q");

            switch (in) {
                case "1" -> {
                    String name = input("Isim");
                    String numberOfFloors = input("Kat Sayisi");

                    Building building = new Building(name, Integer.parseInt(numberOfFloors));
                    buildingRepo.add(building);
                    break;
                }
                case "2" -> {
                    listBuilding();
                    String id = input("ID");
                    buildingRepo.remove(x -> x.getId().equals(id));
                    break;
                }
                case "3" -> {
                    listBuilding();
                    break;
                }
                case "h" -> {
                    outputLine(menuDescriptor);
                    break;
                }
                case "q" -> {
                    run = false;
                    break;
                }

                default -> {
                    FrameFormat errorFrame = new FrameFormat();
                    errorFrame.setTitle("Hata!");
                    errorFrame
                            .appendLine("Gecersiz Secenek!");
                    System.out
                            .print(errorFrame.createFrame());
                    outputLine(menuDescriptor);
                    break;
                }
            }

        }

    }

    public static void occupant() {
        FrameFormat frameFormat = new FrameFormat();
        frameFormat.setTitle("Kiraci Islemleri");

        frameFormat.appendLine("Asagidaki seceneklerden birini seciniz :");
        frameFormat.appendLine("");
        frameFormat.appendLine("1 Kiraci Ekle");
        frameFormat.appendLine("2 Kiraci Cikar");
        frameFormat.appendLine("3 Kiracilari Listele");
        frameFormat.appendLine("h Menuyu Yazdir");
        frameFormat.appendLine("q Menuye Don");
        String menuDescriptor = frameFormat.createFrame();
        outputLine(menuDescriptor);
        boolean run = true;
        while (run) {
            String in = input("(Kiraci Menu) 1 2 3 h q");

            switch (in) {
                case "1" -> {
                    String name = input("Isim");
                    String surname = input("Soyad");
                    listLandlord();
                    String landlordId = input("Ev Sahibi ID");
                    if (!landlordRepo.doesExist(landlordId)) {
                        outputLine("Ev sahibi Bulunamadi");
                        break;
                    }
                    Occupant occupant = new Occupant(name, surname, landlordRepo.getById(landlordId));
                    occupantRepo.add(occupant);
                    break;
                }
                case "2" -> {
                    listOccupant();
                    String id = input("ID");
                    occupantRepo.remove(x -> x.getId().equals(id));
                    break;
                }
                case "3" -> {
                    listOccupant();
                    break;
                }
                case "h" -> {
                    outputLine(menuDescriptor);
                    break;
                }
                case "q" -> {
                    run = false;
                    break;
                }

                default -> {
                    FrameFormat errorFrame = new FrameFormat();
                    errorFrame.setTitle("Hata!");
                    errorFrame
                            .appendLine("Gecersiz Secenek!");
                    System.out
                            .print(errorFrame.createFrame());
                    outputLine(menuDescriptor);
                    break;
                }
            }

        }

    }

    public static void landlord() {
        FrameFormat frameFormat = new FrameFormat();
        frameFormat.setTitle("Ev Sahibi Islemleri");

        frameFormat.appendLine("Asagidaki seceneklerden birini seciniz :");
        frameFormat.appendLine("");
        frameFormat.appendLine("1 Ev Sahibi Ekle");
        frameFormat.appendLine("2 Ev Sahibi Cikar");
        frameFormat.appendLine("3 Ev Sahibine Daire Ekle");
        frameFormat.appendLine("4 Ev Sahiplerini Listele");

        frameFormat.appendLine("h Menuyu Yazdir");
        frameFormat.appendLine("q Menuye Don");
        String menuDescriptor = frameFormat.createFrame();
        outputLine(menuDescriptor);
        boolean run = true;
        while (run) {
            String in = input("(Ev Sahibi Menu) 1 2 3 h q");

            switch (in) {
                case "1" -> {
                    String name = input("Isim");
                    String surname = input("Soyad");
                    listFlat();
                    String flatId = input("Daire ID");
                    if (!flatRepo.doesExist(flatId)) {
                        outputLine("Daire Bulunamadi");
                        break;
                    }
                    landlordRepo.add(new Landlord(name, surname, flatRepo.getById(flatId)));
                    break;
                }
                case "2" -> {
                    listLandlord();
                    String id = input("ID");
                    Landlord landlord = landlordRepo.getById(id);
                    if (landlord == null) {
                        outputLine("Ev Sahibi Bulunamadi");
                        break;
                    }
                    landlordRepo.remove(x -> x.getId().equals(id));
                    break;
                }
                case "3" -> {
                    listLandlord();
                    String id = input("Ev Sahibi ID");
                    if (!landlordRepo.doesExist(id)) {
                        outputLine("Ev Sahibi Bulunamadi");
                        break;
                    }
                    listFlat();
                    String flatId = input("Daire ID");
                    if (!flatRepo.doesExist(flatId)) {
                        outputLine("Daire Bulunamadi");
                        break;
                    }
                    if (!landlordRepo.get(x -> x.getApartments().contains(flatRepo.getById(flatId))).isEmpty()) {
                        outputLine("Bu Daire Baskasina Ait");
                        break;
                    }
                    landlordRepo.getById(id).addApartment(flatRepo.getById(flatId));
                    break;
                }
                case "4" -> {
                    listLandlord();
                    break;
                }
                case "h" -> {
                    outputLine(menuDescriptor);
                    break;
                }
                case "q" -> {
                    run = false;
                    break;
                }

                default -> {
                    FrameFormat errorFrame = new FrameFormat();
                    errorFrame.setTitle("Hata!");
                    errorFrame
                            .appendLine("Gecersiz Secenek!");
                    System.out
                            .print(errorFrame.createFrame());
                    outputLine(menuDescriptor);
                    break;
                }
            }

        }

    }

}
