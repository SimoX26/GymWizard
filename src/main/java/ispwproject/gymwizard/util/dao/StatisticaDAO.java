package ispwproject.gymwizard.util.dao;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class StatisticaDAO {

    private final Connection connection;
    private static final String COLONNA_TOTALE = "totale";

    public StatisticaDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }

    // 1. Numero totale clienti iscritti
    public int getTotaleClienti() throws SQLException {
        String sql = """
            SELECT COUNT(*) 
            FROM Utente U
            JOIN Credenziali C ON U.email = C.email
            WHERE C.ruolo = 1
        """;
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            rs.next();
            return rs.getInt(1);
        }
    }

    // 2. Numero nuovi clienti iscritti nell'ultimo mese
    public int getNuoviClientiUltimoMese() throws SQLException {
        String sql = """
            SELECT COUNT(*) 
            FROM Utente U
            JOIN Credenziali C ON U.email = C.email
            WHERE C.ruolo = 1
              AND U.id IN (
                  SELECT id_cliente 
                  FROM Abbonamento 
                  WHERE data_inizio >= CURDATE() - INTERVAL 1 MONTH
              )
        """;
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            rs.next();
            return rs.getInt(1);
        }
    }

    // 3. Percentuale rinnovo abbonamenti
    public double getPercentualeRinnovi() throws SQLException {
        String sql = """
            SELECT 
                ROUND(
                    (SELECT COUNT(*) FROM Abbonamento WHERE data_rinnovo IS NOT NULL)
                    * 100.0 / 
                    (SELECT COUNT(*) FROM Abbonamento),
                    2
                ) AS percentuale_rinnovo
        """;
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            rs.next();
            return rs.getDouble(1);
        }
    }

    // 4. Attività più prenotata
    public String getAttivitaPiuPrenotata() throws SQLException {
        String sql = """
            SELECT A.nome, COUNT(*) AS totale
            FROM Prenotazione P
            JOIN Attivita A ON P.id_attivita = A.id
            GROUP BY A.nome
            ORDER BY totale DESC
            LIMIT 1
        """;
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getString("nome");
            } else {
                return "Nessuna attività";
            }
        }
    }

    // 5. Fascia oraria più frequentata
    public String getFasciaOrariaPiuFrequentata() throws SQLException {
        String sql = """
            SELECT 
                CONCAT(LPAD(HOUR(A.ora_inizio), 2, '0'), ':00 - ', LPAD(HOUR(A.ora_inizio) + 1, 2, '0'), ':00') AS fascia_oraria,
                COUNT(*) AS totale
            FROM Prenotazione P
            JOIN Attivita A ON P.id_attivita = A.id
            GROUP BY HOUR(A.ora_inizio)
            ORDER BY totale DESC
            LIMIT 1
        """;
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getString("fascia_oraria");
            } else {
                return "Nessuna fascia registrata";
            }
        }
    }

    // 6. Totale entrate mensili dagli abbonamenti
    public double getTotaleEntrateMensili() throws SQLException {
        String sql = """
            SELECT IFNULL(SUM(prezzo), 0) AS totale_entrate
            FROM Abbonamento
            WHERE MONTH(data_rinnovo) = MONTH(CURDATE())
              AND YEAR(data_rinnovo) = YEAR(CURDATE())
        """;
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            rs.next();
            return rs.getDouble("totale_entrate");
        }
    }

    // 7. Numero complessivo di prenotazioni
    public int getTotalePrenotazioni() throws SQLException {
        String sql = "SELECT COUNT(*) FROM Prenotazione";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            rs.next();
            return rs.getInt(1);
        }
    }

    // 8. Numero attività disponibili
    public int getTotaleAttivita() throws SQLException {
        String sql = "SELECT COUNT(*) FROM Attivita";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            rs.next();
            return rs.getInt(1);
        }
    }

    // 9. 📊 Prenotazioni settimanali per giorno
    public Map<String, Integer> getPrenotazioniSettimanali() throws SQLException {
        String sql = """
            SELECT DAYNAME(data_ora) AS giorno, COUNT(*) AS totale
            FROM Prenotazione
            WHERE data_ora >= CURDATE() - INTERVAL 7 DAY
            GROUP BY DAYOFWEEK(data_ora)
            ORDER BY FIELD(DAYNAME(data_ora), 'Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday')
        """;

        Map<String, Integer> map = new LinkedHashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                map.put(rs.getString("giorno"), rs.getInt(COLONNA_TOTALE));
            }
        }
        return map;
    }

    // 10. 📆 Prenotazioni mensili (ultimi 6 mesi)
    public Map<String, Integer> getStoricoPrenotazioniMensili() throws SQLException {
        String sql = """
            SELECT DATE_FORMAT(data_ora, '%Y-%m') AS mese, COUNT(*) AS totale
            FROM Prenotazione
            WHERE data_ora >= CURDATE() - INTERVAL 6 MONTH
            GROUP BY mese
            ORDER BY mese
        """;

        Map<String, Integer> map = new LinkedHashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                map.put(rs.getString("mese"), rs.getInt(COLONNA_TOTALE));
            }
        }
        return map;
    }

    // 11. 💳 Pagamenti mensili (ultimi 6 mesi)
    public Map<String, Double> getStoricoPagamentiMensili() throws SQLException {
        String sql = """
            SELECT DATE_FORMAT(data_rinnovo, '%Y-%m') AS mese, SUM(prezzo) AS totale
            FROM Abbonamento
            WHERE data_rinnovo IS NOT NULL
              AND data_rinnovo >= CURDATE() - INTERVAL 6 MONTH
            GROUP BY mese
            ORDER BY mese
        """;

        Map<String, Double> map = new LinkedHashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                map.put(rs.getString("mese"), rs.getDouble(COLONNA_TOTALE));
            }
        }
        return map;
    }

    // 12. 👤 Utenti attivi ultimi 30 giorni (con almeno una prenotazione)
    public int getUtentiAttiviUltimi30Giorni() throws SQLException {
        String sql = """
            SELECT COUNT(DISTINCT id_cliente)
            FROM Prenotazione
            WHERE data_ora >= CURDATE() - INTERVAL 30 DAY
        """;
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            rs.next();
            return rs.getInt(1);
        }
    }
}
