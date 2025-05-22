package ispwproject.gymwizard.util.DAO;

import ispwproject.gymwizard.model.Credentials;
import ispwproject.gymwizard.model.Role;
import ispwproject.gymwizard.util.exception.DAOException;

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

    public Credentials execute(String username, String password) throws DAOException {
        int roleId;

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement cs = connection.prepareCall("{call login(?,?, ?)}")) {

            cs.setString(1, username);
            cs.setString(2, password); // ⚠️ password in chiaro, coerente con la stored procedure
            cs.registerOutParameter(3, Types.INTEGER);

            cs.execute();
            roleId = cs.getInt(3);

            // 🔐 Controllo fallimento login
            if (roleId == 0) {
                throw new DAOException("Credenziali non valide.");
            }

        } catch (SQLException e) {
            throw new DAOException("Errore durante il login: " + e.getMessage(), e);
        }

        Role role = Role.fromInt(roleId);
        return new Credentials(username, password, role);
    }
}

