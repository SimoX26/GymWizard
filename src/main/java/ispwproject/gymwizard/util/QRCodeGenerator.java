package ispwproject.gymwizard.util;

import java.security.SecureRandom;

public class QRCodeGenerator { // NOSONAR

    private static final String LETTERE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final SecureRandom random = new SecureRandom();

    // Costruttore pubblico: ora la classe è liberamente istanziabile
    public QRCodeGenerator() {}

    // Genera il codice di accesso
    public String generaCodiceAccesso() {
        String lettere = generaLettere(4);
        String numeri = String.format("%04d", random.nextInt(10000)); // 0000–9999
        return lettere + "-" + numeri;
    }

    // Genera la parte alfabetica del codice
    private String generaLettere(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(LETTERE.charAt(random.nextInt(LETTERE.length())));
        }
        return sb.toString();
    }
}
