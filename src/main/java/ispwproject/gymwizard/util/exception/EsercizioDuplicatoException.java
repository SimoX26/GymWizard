package ispwproject.gymwizard.util.exception;

public class EsercizioDuplicatoException extends RuntimeException {
    public EsercizioDuplicatoException(String nomeEsercizio) {
        super("L'esercizio \"" + nomeEsercizio + "\" è già presente nella scheda.");
    }
}
