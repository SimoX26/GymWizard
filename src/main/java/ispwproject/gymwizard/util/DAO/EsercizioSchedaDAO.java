package ispwproject.gymwizard.util.DAO;

import ispwproject.gymwizard.model.EsercizioScheda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EsercizioSchedaDAO {

    // Singleton instance
    private static EsercizioSchedaDAO instance;

    // 🔐Costruttore privato
    public EsercizioSchedaDAO() {}

    // Metodo di accesso pubblico
    public static synchronized EsercizioSchedaDAO getInstance() {
        if (instance == null) {
            instance = new EsercizioSchedaDAO();
        }
        return instance;
    }

    public void insertEsercizio(EsercizioScheda esercizio) {
        String query = "INSERT INTO EsercizioScheda (id_scheda, nome_esercizio, serie, ripetizioni, note) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, esercizio.getIdScheda());
            stmt.setString(2, esercizio.getNomeEsercizio());
            stmt.setInt(3, esercizio.getSerie());
            stmt.setInt(4, esercizio.getRipetizioni());
            stmt.setString(5, esercizio.getNote());

            int affected = stmt.executeUpdate();
            if (affected == 0) return;

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    esercizio.setId(keys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<EsercizioScheda> getEserciziByScheda(int idScheda) {
        List<EsercizioScheda> esercizi = new ArrayList<>();
        String query = "SELECT * FROM EsercizioScheda WHERE id_scheda = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idScheda);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                EsercizioScheda es = new EsercizioScheda(
                        rs.getInt("id_scheda"),
                        rs.getString("nome_esercizio"),
                        rs.getInt("serie"),
                        rs.getInt("ripetizioni"),
                        rs.getString("note")
                );
                esercizi.add(es);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return esercizi;
    }

    public boolean existsEsercizio(int idScheda, String nomeEsercizio) {
        String query = "SELECT COUNT(*) FROM EsercizioScheda WHERE id_scheda = ? AND nome_esercizio = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idScheda);
            stmt.setString(2, nomeEsercizio);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
