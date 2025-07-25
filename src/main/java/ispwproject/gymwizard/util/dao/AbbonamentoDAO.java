package ispwproject.gymwizard.util.dao;

import ispwproject.gymwizard.model.Abbonamento;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.logger.AppLogger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class AbbonamentoDAO {

    // Costruttore pubblico: stateless, Sonar-friendly
    public AbbonamentoDAO() {}

    public Abbonamento trovaAbbonamentoAttivoPerUtente(int idUtente) {
        Abbonamento abbonamento = null;

        String query = """
            SELECT id, tipo, data_inizio, data_fine, stato, riferimento_pagamento
            FROM Abbonamento
            WHERE id_utente = ? AND stato = 'attivo'
            LIMIT 1
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idUtente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    abbonamento = new Abbonamento();
                    abbonamento.setId(rs.getInt("id"));
                    abbonamento.setTipo(rs.getString("tipo"));
                    abbonamento.setDataInizio(rs.getDate("data_inizio").toLocalDate());
                    abbonamento.setDataFine(rs.getDate("data_fine").toLocalDate());
                    abbonamento.setStato(rs.getString("stato"));
                    abbonamento.setRiferimentoPagamento(rs.getString("riferimento_pagamento"));
                }
            }

        } catch (SQLException e) {
            AppLogger.getLogger().log(Level.SEVERE, e,
                    () -> "Errore durante il recupero dell'abbonamento attivo per l'utente " + idUtente);
        }

        return abbonamento;
    }

    public void inserisciAbbonamento(Abbonamento abbonamento) {
        String query = """
            INSERT INTO Abbonamento (id_utente, tipo, data_inizio, data_fine, stato, riferimento_pagamento)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, abbonamento.getIdUtente());
            stmt.setString(2, abbonamento.getTipo());
            stmt.setDate(3, Date.valueOf(abbonamento.getDataInizio()));
            stmt.setDate(4, Date.valueOf(abbonamento.getDataFine()));
            stmt.setString(5, abbonamento.getStato());
            stmt.setString(6, abbonamento.getRiferimentoPagamento());

            stmt.executeUpdate();

        } catch (SQLException e) {
            AppLogger.getLogger().log(Level.SEVERE, e,
                    () -> "Errore durante l'inserimento dell'abbonamento per l'utente " + abbonamento.getIdUtente());
        }
    }

    public List<Abbonamento> getAllDisponibili() throws DAOException {
        List<Abbonamento> abbonamentoList = new ArrayList<>();

        String query = """
            SELECT id, data_inizio, data_fine, tipo, stato
            FROM Abbonamento
            WHERE data_inizio >= CURDATE()
            ORDER BY data_inizio
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Abbonamento abbonamento = new Abbonamento(
                        rs.getInt("id"),
                        rs.getDate("data_inizio").toLocalDate(),
                        rs.getDate("data_fine").toLocalDate(),
                        rs.getString("tipo"),
                        rs.getString("stato")
                );
                abbonamentoList.add(abbonamento);
            }

        } catch (SQLException e) {
            throw new DAOException("Errore durante il recupero degli abbonamenti: " + e.getMessage(), e);
        }

        return abbonamentoList;
    }
}
