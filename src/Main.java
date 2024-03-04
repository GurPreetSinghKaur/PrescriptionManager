import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Utility utility = new Utility();
        Scanner scanner = new Scanner(System.in);
        String result =   utility.addString(scanner, "Try a string");



        Menu menu = Menu.getMenu();
        menu.start();




    }
}