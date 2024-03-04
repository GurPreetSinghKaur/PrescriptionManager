import java.util.Scanner;
public final class Utility {
private Utility(){}

    public static String addString (String message){
     Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        String temp = scanner.nextLine(); //Get new input

        if (temp.isEmpty()) {
            System.out.println("enter detected");
        return "";
        }

        return temp;
    }
    public static int addInt (String message){
    System.out.println(message);
    Scanner scanner = new Scanner(System.in);

        while(!scanner.hasNextInt()){
            scanner.next();
            System.out.println("Enter numbers e.g. 1 2 3...");
        };

    return scanner.nextInt();
    }
}