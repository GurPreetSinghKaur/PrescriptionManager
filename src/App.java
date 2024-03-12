
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author postgresqltutorial.com
 */
public class App{
    private final static Logger logger = Logger.getLogger(Logger.class.getName());
    private final String url = "jdbc:postgresql://localhost/prescription_manager";
    private final String user = "raja";
    private final String password = "";

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            //System.out.println("Connected to the PostgreSQL server successfully.");
            logger.log(Level.INFO, "Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }



}
