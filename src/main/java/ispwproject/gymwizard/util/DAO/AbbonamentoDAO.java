package ispwproject.gymwizard.util.DAO;

import ispwproject.gymwizard.model.Abbonamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AbbonamentoDAO {

    public static Abbonamento trovaAbbonamentoAttivoPerUtente(int idUtente) {
        Abbonamento abbonamento = null;

        String query = "SELECT * FROM Abbonamento " +
                "WHERE id_utente = ? " +
                "AND stato = 'attivo' " +
                "LIMIT 1";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idUtente);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                abbonamento = new Abbonamento();
                abbonamento.setId(rs.getInt("id"));
                abbonamento.setTipo(rs.getString("tipo"));
                abbonamento.setDataInizio(rs.getDate("data_inizio").toLocalDate());
                abbonamento.setDataFine(rs.getDate("data_fine").toLocalDate());
                abbonamento.setStato(rs.getString("stato"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return abbonamento;
    }

}
