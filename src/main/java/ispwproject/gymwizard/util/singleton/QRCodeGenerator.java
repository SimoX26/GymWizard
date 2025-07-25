package ispwproject.gymwizard.util.singleton;

import java.security.SecureRandom;

public class QRCodeGenerator { //NOSONAR

    private static final String LETTERE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final SecureRandom random = new SecureRandom();

    // Costruttore privato: impedisce l'istanziazione esterna
    private QRCodeGenerator() {}

    // Lazy Holder: inizializzazione solo al primo accesso
    private static class Holder {
        private static final QRCodeGenerator INSTANCE = new QRCodeGenerator();
    }

    // Accesso all'unica istanza
    public static QRCodeGenerator getInstance() {
        return Holder.INSTANCE;
    }

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
