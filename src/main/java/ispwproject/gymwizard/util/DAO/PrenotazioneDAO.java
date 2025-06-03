package ispwproject.gymwizard.util.DAO;

import ispwproject.gymwizard.model.Prenotazione;
import ispwproject.gymwizard.util.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrenotazioneDAO {

    private static PrenotazioneDAO instance;

    private PrenotazioneDAO() {}

    public static PrenotazioneDAO getInstance() {
        if (instance == null) {
            instance = new PrenotazioneDAO();
        }
        return instance;
    }

    public List<Prenotazione> getAll() throws DAOException {
        List<Prenotazione> lista = new ArrayList<>();

        String query = "SELECT * FROM Prenotazione";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Prenotazione p = new Prenotazione();
                p.setId(rs.getInt("id"));
                p.setIdAttivita(rs.getInt("id_attivita"));
                p.setIdCliente(rs.getInt("id_cliente"));
                Timestamp timestamp = rs.getTimestamp("data_creazione");
                if (timestamp != null) {
                    p.setDataCreazione(timestamp.toLocalDateTime());
                }

                lista.add(p);
            }

        } catch (SQLException e) {
            throw new DAOException("Errore nel recupero delle prenotazioni", e);
        }

        return lista;
    }

    public void inserisciPrenotazione(int idAttivita, int idCliente) throws DAOException {
        String query = "INSERT INTO Prenotazione (id_attivita, id_cliente) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idAttivita);
            stmt.setInt(2, idCliente);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Errore durante l'inserimento della prenotazione", e);
        }
    }
}

