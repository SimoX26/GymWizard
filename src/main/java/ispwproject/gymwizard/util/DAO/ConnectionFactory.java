package ispwproject.gymwizard.util.DAO;

import ispwproject.gymwizard.model.Role;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static Connection connection;
    private static final String PROPERTIES_FILE = "src/main/resources/db.properties";
    private static final Properties properties = new Properties();

    private ConnectionFactory() {}

    static {
        try (InputStream input = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(input);
            initConnection(); // login generico
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Errore durante il caricamento di db.properties: " + e.getMessage());
        }
    }

    private static void initConnection() throws SQLException {
        String url = properties.getProperty("CONNECTION_URL");
        String user = properties.getProperty("LOGIN_USER");
        String pass = properties.getProperty("LOGIN_PASS");
        connection = DriverManager.getConnection(url, user, pass);
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            initConnection();
        }
        return connection;
    }

    public static void changeRole(Role role) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }

        try (InputStream input = new FileInputStream(PROPERTIES_FILE)) {
            Properties localProps = new Properties();
            localProps.load(input);

            String url = localProps.getProperty("CONNECTION_URL");
            String userKey = role.name() + "_USER";
            String passKey = role.name() + "_PASS";

            String user = localProps.getProperty(userKey);
            String pass = localProps.getProperty(passKey);

            if (user == null || pass == null) {
                throw new SQLException("Credenziali mancanti per il ruolo: " + role.name());
            }

            connection = DriverManager.getConnection(url, user, pass);
        } catch (IOException e) {
            e.printStackTrace();
            throw new SQLException("Errore durante il cambio di ruolo", e);
        }
    }
}
