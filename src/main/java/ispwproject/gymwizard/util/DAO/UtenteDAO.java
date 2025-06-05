package ispwproject.gymwizard.util.DAO;

import ispwproject.gymwizard.model.Utente;

import java.sql.*;

public class UtenteDAO {

    public static Utente getByEmail(String email) {
        Utente utente = null;
        String query = "SELECT * FROM Utente WHERE email = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String emailDb = rs.getString("email");

                utente = new Utente(id, username, emailDb);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // oppure loggalo con un logger
        }

        return utente;
    }

    public boolean insertUtente(Utente utente) {
        String query = "INSERT INTO Utente (username, email) VALUES (?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, utente.getUsername());
            stmt.setString(2, utente.getEmail());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) return false;

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    utente.setId(generatedKeys.getInt(1));
                }
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
