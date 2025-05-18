package ispwproject.gymwizard.util.DAO;

import ispwproject.gymwizard.util.bean.LoginBean;
import ispwproject.gymwizard.util.bean.SessionBean;
import ispwproject.gymwizard.util.HashUtil;

import java.sql.*;
import java.util.UUID;

public class SessionDAO {

    public SessionBean userLogin(LoginBean login) {
        SessionBean session = null;

        if (login != null) {
            try (Connection conn = DatabaseConnection.getConnection()) {
                String query = "SELECT username, ruolo, password_hash FROM Utente WHERE email = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, login.getEmail());

                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        String storedHash = rs.getString("password_hash");
                        String inputHash = HashUtil.hashPassword(login.getPassword());

                        // 🔍 Debug: stampa hash inserito e hash salvato nel DB
                        System.out.println("▶ HASH INSERITO : " + inputHash);
                        System.out.println("▶ HASH DAL DB   : " + storedHash);

                        if (storedHash.equals(storedHash)) {
                            String sid = UUID.randomUUID().toString();
                            String ruolo = rs.getString("ruolo");
                            String username = rs.getString("username");

                            session = new SessionBean(sid, ruolo);
                            session.setUsername(username);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return session;
    }

    public SessionBean userLoginGoogle(String email, String nome, String cognome) {
        SessionBean session = null;

        try (Connection conn = DatabaseConnection.getConnection()) {
            String checkQuery = "SELECT username, ruolo FROM Utente WHERE email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(checkQuery)) {
                stmt.setString(1, email);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String username = rs.getString("username");
                    String ruolo = rs.getString("ruolo");
                    session = new SessionBean(UUID.randomUUID().toString(), ruolo);
                    session.setUsername(username);
                } else {
                    // Registrazione automatica nuovo utente Google come cliente
                    String generatedUsername = nome.toLowerCase() + "." + cognome.toLowerCase();
                    String insertQuery = "INSERT INTO Utente (username, email, ruolo, login_google) VALUES (?, ?, 'cliente', TRUE)";
                    try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                        insertStmt.setString(1, generatedUsername);
                        insertStmt.setString(2, email);
                        insertStmt.executeUpdate();

                        session = new SessionBean(UUID.randomUUID().toString(), "cliente");
                        session.setUsername(generatedUsername);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return session;
    }
}
