package ispwproject.gymwizard.util.dao;

import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.bean.UtenteAttivoBean;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.logger.AppLogger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class UtenteDAO {

    private static final String COL_USERNAME = "username";
    private static final String COL_EMAIL = "email";


    public static Utente getByEmail(String email) {
        Utente utente = null;
        String query = "SELECT id, username, email FROM Utente WHERE email = ?";


        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString(COL_USERNAME);
                String emailDb = rs.getString(COL_EMAIL);

                utente = new Utente(id, username, emailDb);
            }

        } catch (SQLException e) {
            AppLogger.getLogger().log(Level.SEVERE, "Errore durante il recupero dell’utente con email: " + email, e);
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
            AppLogger.getLogger().log(Level.SEVERE, "Errore durante l’inserimento dell’utente: " + utente.getEmail(), e);
            return false;
        }
    }

    public List<Utente> getClienti() throws SQLException {
        List<Utente> clienti = new ArrayList<>();

        String query = """
        SELECT u.id, u.username, u.email, u.ultimo_accesso
        FROM Utente u
        JOIN Credenziali c ON u.email = c.email
        WHERE c.ruolo = 1
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Utente u = new Utente(
                        rs.getInt("id"),
                        rs.getString(COL_USERNAME),
                        rs.getString(COL_EMAIL)
                );
                clienti.add(u);
            }
        }

        return clienti;
    }


    /**
     * Metodo per il report admin: recupera gli utenti attivi negli ultimi 30 giorni.
     */
    public List<UtenteAttivoBean> getUtentiAttivi() throws DAOException {
        List<UtenteAttivoBean> lista = new ArrayList<>();

        String query = """
            SELECT username, email, ultimo_accesso
            FROM Utente
            WHERE ultimo_accesso >= NOW() - INTERVAL 30 DAY
            ORDER BY ultimo_accesso DESC
        """;

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String username = rs.getString(COL_USERNAME);
                String nomeCompleto = username + " (" + rs.getString(COL_EMAIL) + ")";
                String ultimoAccesso = rs.getString("ultimo_accesso");

                lista.add(new UtenteAttivoBean(username, nomeCompleto, ultimoAccesso));
            }

        } catch (SQLException e) {
            throw new DAOException("Errore nel recupero degli utenti attivi", e);
        }

        return lista;
    }
}
