import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        }

        return scanner.nextInt();
    }

    public static String addDate (String message, boolean skippable)
    {
        System.out.println(message);
        String date = "";
        String regex = "(0[1-9]|1[0-9]|2[0-9]|3[0-1]|[1-9])/(0[1-9]|1[0-2]|[1-9])/([0-9]{4})";
        Pattern pattern = Pattern.compile(regex);
if (skippable) {
    date = addString("");
    if (date.isEmpty()) return "";
} else {
    Scanner scanner = new Scanner(System.in);
    date = scanner.next();
}
        Matcher matcher = pattern.matcher(date);
        while (!matcher.matches()) {
        date = addString("Wrong date, please make sure it follows the format dd/mm/yyyy e.g. 31/12/1999");
        matcher = pattern.matcher(date);
            }
        return date;
    }
}