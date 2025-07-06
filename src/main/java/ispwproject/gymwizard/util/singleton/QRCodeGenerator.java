package ispwproject.gymwizard.util.singleton;

import java.security.SecureRandom;

public class QRCodeGenerator { // NOSONAR

    private static QRCodeGenerator instance;
    private static final String LETTERE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final SecureRandom random = new SecureRandom();

    private QRCodeGenerator() {}

    public static synchronized QRCodeGenerator getInstance() {
        if (instance == null) {
            instance = new QRCodeGenerator();
        }
        return instance;
    }

    public String generaCodiceAccesso() {
        String lettere = generaLettere(4);
        String numeri = String.format("%04d", random.nextInt(10000)); // 0000–9999
        return lettere + "-" + numeri;
    }

    private String generaLettere(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(LETTERE.charAt(random.nextInt(LETTERE.length())));
        }
        return sb.toString();
    }
}
