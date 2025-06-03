package ispwproject.gymwizard.util.DAO;

import ispwproject.gymwizard.util.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteDAO {
    public int getIdByEmail(String email) throws DAOException {
        String query = "SELECT id FROM Utente WHERE email = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new DAOException("Nessun utente trovato con email: " + email);
            }

        } catch (SQLException e) {
            throw new DAOException("Errore durante il recupero dell'ID utente", e);
        }
    }

}
