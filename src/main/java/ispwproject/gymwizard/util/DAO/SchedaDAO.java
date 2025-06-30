package ispwproject.gymwizard.util.DAO;

import ispwproject.gymwizard.model.Scheda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SchedaDAO {

    // Istanza singleton
    private static SchedaDAO instance;

    // Costruttore privato
    private SchedaDAO() {}

    // 🚪Metodo di accesso pubblico
    public static synchronized SchedaDAO getInstance() {
        if (instance == null) {
            instance = new SchedaDAO();
        }
        return instance;
    }

    public boolean insertScheda(Scheda scheda) {
        String query = "INSERT INTO Scheda (id_cliente, nome_esercizio) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, scheda.getIdCliente());
            stmt.setString(2, scheda.getNomeScheda());

            int affected = stmt.executeUpdate();
            if (affected == 0) return false;

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    scheda.setId(keys.getInt(1));
                }
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Scheda> getSchedeByUtente(int idCliente) {
        List<Scheda> schede = new ArrayList<>();
        String query = "SELECT * FROM Scheda WHERE id_cliente = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Scheda s = new Scheda(
                        rs.getInt("id"),
                        rs.getInt("id_cliente"),
                        rs.getString("nome_esercizio"),
                        rs.getTimestamp("data_creazione")
                );
                schede.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return schede;
    }
}
