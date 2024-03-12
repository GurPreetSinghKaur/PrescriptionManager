import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppLogger {
   // private final static Logger logger = Logger.getLogger(AppLogger.class.getName());
    private static FileHandler fh = null;
    static {
        init();
    }
    public static void init(){
        try {
            fh = new FileHandler("logger.log", true);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        Logger l = Logger.getLogger("");
        fh.setFormatter(new SimpleFormatter());
        l.addHandler(fh);
        l.setLevel(Level.CONFIG);
    }

}