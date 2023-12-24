import java.util.Scanner;

public class Main {

    Scanner scanner = new Scanner(System.in);

    public String input(String desc) {
        System.out.print(desc + " > ");
        return scanner.nextLine().trim();

    }

    public void outputLine(String str) {
        System.out.println(str);
    }

    public static void main(String[] args) {
        Menus.mainMenu();

    }
}