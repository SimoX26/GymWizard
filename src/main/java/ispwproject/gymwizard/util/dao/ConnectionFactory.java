package ispwproject.gymwizard.util.dao;

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
    private static Role currentRole = null; // 🔑 tiene traccia del ruolo attivo
    private static final String PROPERTIES_FILE = "src/main/resources/db.properties";
    private static final Properties properties = new Properties();

    private ConnectionFactory() {}

    static {
        try (InputStream input = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(input);
        } catch (IOException e) {
            throw new ExceptionInInitializerError();
        }
    }


    private static void initConnection() throws SQLException {
        String url = properties.getProperty("CONNECTION_URL");
        String user;
        String pass;

        if (currentRole != null) {
            user = properties.getProperty(currentRole.name() + "_USER");
            pass = properties.getProperty(currentRole.name() + "_PASS");
        } else {
            user = properties.getProperty("LOGIN_USER");
            pass = properties.getProperty("LOGIN_PASS");
        }

        if (user == null || pass == null) {
            throw new SQLException("Credenziali mancanti per il ruolo attuale");
        }

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

        currentRole = role; // ✅ aggiorna il ruolo attivo
        initConnection();
    }

    public static void clearRole() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
        currentRole = null;
    }
}
