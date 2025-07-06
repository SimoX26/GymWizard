package ispwproject.gymwizard.util;

import ispwproject.gymwizard.util.exception.HashingException;

import java.security.MessageDigest;

public class HashUtil {

    private HashUtil() {
        // Impedisce istanziazione
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashed = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashed)
                sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            throw new HashingException("Errore durante l'hashing della password", e);
        }
    }
}
