package org.example.DAO;

import org.example.beans.LoginBean;
import org.example.beans.SessionBean;
import org.example.HashUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

                        if (storedHash.equals(inputHash)) {
                            String sid = java.util.UUID.randomUUID().toString();
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
}
