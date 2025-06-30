package ispwproject.gymwizard.util.DAO;

import ispwproject.gymwizard.util.bean.PagamentoBean;
import ispwproject.gymwizard.util.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {

    public List<PagamentoBean> getStoricoPagamenti() throws DAOException {
        List<PagamentoBean> lista = new ArrayList<>();

        String query = """
            SELECT u.username, a.tipo, a.data_inizio, a.data_fine, a.stato, a.riferimento_pagamento
            FROM Abbonamento a
            JOIN Utente u ON a.id_utente = u.id
            ORDER BY a.data_inizio DESC
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String tipo = rs.getString("tipo");
                String data_inizio = rs.getString("data_inizio");
                String data_fine = rs.getString("data_fine");
                String stato = rs.getString("stato");
                String riferimento = rs.getString("riferimento_pagamento");

                lista.add(new PagamentoBean(tipo, data_inizio, data_fine, stato, riferimento));
            }

        } catch (SQLException e) {
            throw new DAOException("Errore nel recupero dello storico pagamenti", e);
        }

        return lista;
    }
}
