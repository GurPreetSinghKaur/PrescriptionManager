import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
     //private static final Logger logger = Logger.getLogger("");
     private final static Logger logger = Logger.getLogger(Logger.class.getName());

    public static void main(String[] args) {
        AppLogger.init();
        logger.log(Level.INFO,"The application has been started");
        Menu menu = Menu.getMenu();
        menu.start();
    }
}