package ispwproject.gymwizard.util.DAO;

import ispwproject.gymwizard.model.Credentials;
import ispwproject.gymwizard.model.Role;
import ispwproject.gymwizard.util.bean.SessionBean;
import ispwproject.gymwizard.util.HashUtil;

import java.sql.*;
import java.util.UUID;

public class SessionDAO {

    public SessionBean userLogin(Credentials login) {
        SessionBean session = null;

        if (login != null) {
            try (Connection conn = ConnectionFactory.getConnection()) {
                String query = "SELECT username, ruolo, password_hash FROM Utente WHERE email = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, login.getEmail());

                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        String storedHash = rs.getString("password_hash");
                        String inputHash = HashUtil.hashPassword(login.getPassword());

                        if (storedHash.equals(inputHash)) {
                            String sid = UUID.randomUUID().toString();
                            String ruoloStr = rs.getString("ruolo").toUpperCase(); // meglio maiuscolo per sicurezza
                            String username = rs.getString("username");

                            Role ruolo = Role.valueOf(ruoloStr); // converte da String a enum Role
                            session = new SessionBean(sid, ruolo);
                            session.setUsername(username); // assicurati che questo metodo esista
                        }
                    }
                }
            } catch (SQLException | IllegalArgumentException e) {
                e.printStackTrace(); // puoi gestire meglio l'eccezione in produzione
            }
        }

        return session;
    }
}

