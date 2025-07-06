package ispwproject.gymwizard.util.dao;

import ispwproject.gymwizard.model.Credentials;
import ispwproject.gymwizard.model.Role;
import ispwproject.gymwizard.util.exception.CredenzialiException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class LoginProcedureDAO {

    private static LoginProcedureDAO instance = null;

    private LoginProcedureDAO() {}

    public static LoginProcedureDAO getInstance() {
        if (instance == null) {
            instance = new LoginProcedureDAO();
        }
        return instance;
    }

    public Credentials execute(String email, String passwordPlainText) throws CredenzialiException {
        int roleId;

        String passwordHash = hashPassword(passwordPlainText); // SHA-256 hashing

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement cs = connection.prepareCall("{call login(?, ?, ?)}")) {

            cs.setString(1, email);
            cs.setString(2, passwordHash); // hashed password
            cs.registerOutParameter(3, Types.INTEGER);

            cs.execute();
            roleId = cs.getInt(3);

            if (roleId == 0) {
                throw new CredenzialiException("Credenziali non valide. Riprova.");
            }

        } catch (SQLException e) {
            throw new CredenzialiException("Errore durante il processo di login. Contatta l'assistenza. Dettagli: " + e.getMessage());
        }

        Role role = Role.fromInt(roleId);
        return new Credentials(email, passwordHash, role);
    }

    private String hashPassword(String password) throws CredenzialiException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new CredenzialiException("Errore interno durante la codifica della password.");
        }
    }
}

