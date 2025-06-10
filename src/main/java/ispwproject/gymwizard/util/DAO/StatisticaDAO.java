package ispwproject.gymwizard.util.DAO;

import java.sql.*;

public class StatisticaDAO {

    private final Connection connection;

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

    // Numero complessivo di prenotazioni
    public int getTotalePrenotazioni() throws SQLException {
        String sql = "SELECT COUNT(*) FROM Prenotazione";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            rs.next();
            return rs.getInt(1);
        }
    }

    // Numero attività disponibili (nessun campo `visibile` nel DB → rimuoviamo filtro)
    public int getTotaleAttivita() throws SQLException {
        String sql = "SELECT COUNT(*) FROM Attivita";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            rs.next();
            return rs.getInt(1);
        }
    }
}
