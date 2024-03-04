import java.util.Scanner;

public final class Utility {
private Utility(){}

    public static String addString (Scanner scanner, String message){
        System.out.println(message);
        String temp = scanner.nextLine(); //Get new input

        if (temp.isEmpty()) {
            System.out.println("enter detected");
        return "";
        }

        return temp;
    }

}
