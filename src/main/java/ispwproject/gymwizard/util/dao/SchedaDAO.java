package ispwproject.gymwizard.util.dao;

import ispwproject.gymwizard.model.Scheda;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.logger.AppLogger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


public class SchedaDAO { // NOSONAR

    // Istanza singleton
    private static SchedaDAO instance;

    // Costruttore privato per impedire l’istanziazione esterna
    private SchedaDAO() {}

    public static synchronized SchedaDAO getInstance() {
        if (instance == null) {
            instance = new SchedaDAO();
        }
        return instance;
    }

    public void insertScheda(Scheda scheda) throws DAOException {
        String query = "INSERT INTO Scheda (id_cliente, nome_scheda) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, scheda.getIdCliente());
            stmt.setString(2, scheda.getNomeScheda());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Errore durante l'inserimento della scheda", e);
        }
    }

    public List<Scheda> getSchedeByUtente(int idCliente) {
        List<Scheda> schede = new ArrayList<>();
        String query = "SELECT id, id_cliente, nome_scheda, data_creazione FROM Scheda WHERE id_cliente = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Scheda s = new Scheda(
                        rs.getInt("id"),
                        rs.getInt("id_cliente"),
                        rs.getString("nome_scheda"),
                        rs.getTimestamp("data_creazione")
                );
                schede.add(s);
            }

        } catch (SQLException e) {
            AppLogger.getLogger().log(Level.SEVERE, e, () -> "Errore durante il recupero delle schede per l'utente " + idCliente);
        }



        return schede;
    }
}
