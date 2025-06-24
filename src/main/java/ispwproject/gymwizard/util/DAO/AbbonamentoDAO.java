package ispwproject.gymwizard.util.DAO;

import ispwproject.gymwizard.model.Abbonamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AbbonamentoDAO {

    private static AbbonamentoDAO instance;

    private AbbonamentoDAO() {
        // Costruttore privato per impedire l'istanziazione esterna
    }

    public static synchronized AbbonamentoDAO getInstance() {
        if (instance == null) {
            instance = new AbbonamentoDAO();
        }
        return instance;
    }

    public Abbonamento trovaAbbonamentoAttivoPerUtente(int idUtente) {
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

    public boolean inserisciAbbonamento(Abbonamento abbonamento) {
        String query = """
        INSERT INTO Abbonamento (id_utente, tipo, data_inizio, data_fine, stato, riferimento_pagamento)
        VALUES (?, ?, ?, ?, ?, ?)
    """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, abbonamento.getIdUtente());
            stmt.setString(2, abbonamento.getTipo());
            stmt.setDate(3, java.sql.Date.valueOf(abbonamento.getDataInizio()));
            stmt.setDate(4, java.sql.Date.valueOf(abbonamento.getDataFine()));
            stmt.setString(5, abbonamento.getStato());
            stmt.setString(6, abbonamento.getRiferimentoPagamento());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
