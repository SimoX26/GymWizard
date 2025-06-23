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
            SELECT u.username, a.data_rinnovo, a.prezzo, a.riferimento_pagamento
            FROM Abbonamento a
            JOIN Utente u ON a.id_utente = u.id
            ORDER BY a.data_rinnovo DESC
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String data = rs.getString("data_rinnovo");
                String importo = rs.getString("prezzo");
                String metodo = rs.getString("riferimento_pagamento");

                lista.add(new PagamentoBean(data, importo, metodo));
            }

        } catch (SQLException e) {
            throw new DAOException("Errore nel recupero dello storico pagamenti", e);
        }

        return lista;
    }
}
