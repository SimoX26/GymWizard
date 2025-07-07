package ispwproject.gymwizard.util.dao;

import ispwproject.gymwizard.model.Attivita;
import ispwproject.gymwizard.util.exception.DAOException;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AttivitaDAO { // NOSONAR

    private AttivitaDAO() {}

    private static class Holder {
        private static final AttivitaDAO INSTANCE = new AttivitaDAO();
    }

    public static AttivitaDAO getInstance() {
        return Holder.INSTANCE;
    }

    public List<Attivita> getAllDisponibili() throws DAOException {
        List<Attivita> attivitaList = new ArrayList<>();

        String query = "SELECT id, nome, descrizione, data, ora_inizio, ora_fine, posti_disponibili, trainer_name " +
                "FROM Attivita WHERE data >= CURDATE() ORDER BY data, ora_inizio";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Attivita attivita = new Attivita(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descrizione"),
                        rs.getDate("data").toLocalDate(),
                        rs.getTime("ora_inizio").toLocalTime(),
                        rs.getTime("ora_fine").toLocalTime(),
                        rs.getInt("posti_disponibili"),
                        rs.getString("trainer_name")
                );
                attivitaList.add(attivita);
            }

        } catch (SQLException e) {
            throw new DAOException("Errore durante il recupero delle attività: " + e.getMessage(), e);
        }

        return attivitaList;
    }

    public void inserisciAttivita(Attivita attivita) throws DAOException {
        String query = """
            INSERT INTO Attivita (nome, descrizione, data, ora_inizio, ora_fine, posti_disponibili, trainer_name)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, attivita.getNome());
            ps.setString(2, attivita.getDescrizione());
            ps.setDate(3, Date.valueOf(attivita.getData()));
            ps.setTime(4, Time.valueOf(attivita.getOraInizio()));
            ps.setTime(5, Time.valueOf(attivita.getOraFine()));
            ps.setInt(6, attivita.getPostiDisponibili());
            ps.setString(7, attivita.getTrainerName());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Errore durante l'inserimento dell'attività", e);
        }
    }

    public boolean existsAttivita(String nome, LocalDate data, LocalTime oraInizio) throws DAOException {
        String query = """
            SELECT COUNT(*) FROM Attivita
            WHERE nome = ? AND data = ? AND ora_inizio = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, nome);
            ps.setDate(2, Date.valueOf(data));
            ps.setTime(3, Time.valueOf(oraInizio));

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            throw new DAOException("Errore durante il controllo attività duplicata: " + e.getMessage(), e);
        }
    }
}
