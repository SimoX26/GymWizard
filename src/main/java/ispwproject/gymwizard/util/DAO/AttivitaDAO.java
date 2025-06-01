package ispwproject.gymwizard.util.DAO;

import ispwproject.gymwizard.model.Attivita;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.singleton.SessionManager;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AttivitaDAO {

    private static AttivitaDAO instance;

    private AttivitaDAO() {}

    public static AttivitaDAO getInstance() {
        if (instance == null) {
            instance = new AttivitaDAO();
        }
        return instance;
    }

    public List<Attivita> getAllDisponibili() throws DAOException {
        List<Attivita> attivitaList = new ArrayList<>();

        String query = "SELECT * FROM Attivita WHERE data >= CURDATE() ORDER BY data, ora_inizio";

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
        String query = "INSERT INTO Attivita (nome, descrizione, data, ora_inizio, ora_fine, posti_disponibili, trainer_name) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

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

}
