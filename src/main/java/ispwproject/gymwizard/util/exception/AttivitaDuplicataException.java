package ispwproject.gymwizard.util.exception;

public class AttivitaDuplicataException extends Exception {
    public AttivitaDuplicataException(String nomeAttivita) {
        super("Esiste già un'attività con il nome \"" + nomeAttivita + "\" per quel giorno/ora.");
    }

    public AttivitaDuplicataException() {
        super("Attività già registrata in quel giorno e orario.");
    }
}
