package ispwproject.gymwizard.util.exception;

public class CredenzialiException extends Exception {
    public CredenzialiException() {
        super("Credenziali non valide. Verifica username e password.");
    }

    public CredenzialiException(String dettaglio) {
        super("Errore di autenticazione: " + dettaglio);
    }
}
