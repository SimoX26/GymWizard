package ispwproject.gymwizard.util.dao;

import ispwproject.gymwizard.model.Prenotazione;
import ispwproject.gymwizard.util.bean.PrenotazioneBean;
import ispwproject.gymwizard.util.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrenotazioneDAO {

    // Istanza singleton
    private static PrenotazioneDAO instance;

    // Costruttore privato
    private PrenotazioneDAO() {}

    // Metodo statico per ottenere l'istanza singleton
    public static synchronized PrenotazioneDAO getInstance() {
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

    public void add(int idAttivita, int idCliente) throws DAOException {
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

    public List<PrenotazioneBean> getStoricoPrenotazioni() throws DAOException {
        List<PrenotazioneBean> lista = new ArrayList<>();

        String query = """
            SELECT u.username, a.nome AS attivita, p.data_creazione, p.stato
            FROM Prenotazione p
            JOIN Utente u ON p.id_cliente = u.id
            JOIN Attivita a ON p.id_attivita = a.id
            ORDER BY p.data_creazione DESC
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new PrenotazioneBean(
                        rs.getString("data_creazione"),
                        rs.getString("attivita"),
                        rs.getString("stato")
                ));
            }

        } catch (SQLException e) {
            throw new DAOException("Errore nel recupero dello storico prenotazioni", e);
        }

        return lista;
    }
}
