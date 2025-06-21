package ispwproject.gymwizard.util.DAO;

import ispwproject.gymwizard.model.EsercizioScheda;
import ispwproject.gymwizard.util.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EsercizioSchedaDAO {

    public List<EsercizioScheda> getEserciziByClientId(int idCliente) throws DAOException {
        List<EsercizioScheda> esercizi = new ArrayList<>();

        String query = """
        SELECT es.*
        FROM esercizio_scheda es
        JOIN scheda s ON es.id_scheda = s.id
        WHERE s.id_cliente = ?
    """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idCliente);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    EsercizioScheda es = new EsercizioScheda();
                    es.setId(rs.getInt("id"));
                    es.setId_scheda(rs.getInt("id_scheda"));
                    es.setId_esercizio(rs.getInt("id_esercizio"));
                    es.setGiorno_settimana(rs.getString("giorno_settimana"));
                    es.setSerie(rs.getInt("serie"));
                    es.setRipetizioni(rs.getInt("ripetizioni"));
                    es.setNote(rs.getString("note"));
                    esercizi.add(es);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Errore nel recupero degli esercizi della scheda del cliente", e);
        }

        return esercizi;
    }


    public void insertEsercizio(EsercizioScheda esercizio) throws DAOException {
        String query = "INSERT INTO esercizio_scheda (id_scheda, id_esercizio, giorno_settimana, serie, ripetizioni, note) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, esercizio.getId_scheda());
            stmt.setInt(2, esercizio.getId_esercizio());
            stmt.setString(3, esercizio.getGiorno_settimana());
            stmt.setInt(4, esercizio.getSerie());
            stmt.setInt(5, esercizio.getRipetizioni());
            stmt.setString(6, esercizio.getNote());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Errore durante l'inserimento dell'esercizio nella scheda", e);
        }
    }

    // Aggiungibili anche: updateEsercizio, deleteEsercizio, getById se necessario
}
