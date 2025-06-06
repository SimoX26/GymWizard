package ispwproject.gymwizard.util.exception;

public class AbbonamentoScadutoException extends Exception {
    public AbbonamentoScadutoException(String nomeUtente) {
        super("L'abbonamento dell'utente \"" + nomeUtente + "\" risulta scaduto. È necessario rinnovarlo.");
    }

    public AbbonamentoScadutoException() {
        super("Abbonamento scaduto. Impossibile accedere ai servizi.");
    }
}
