package ispwproject.gymwizard.util.exception;

public class AttivitaPienaException extends Exception {
  public AttivitaPienaException(String nomeAttivita) {
    super("L'attività \"" + nomeAttivita + "\" ha raggiunto il numero massimo di partecipanti.");
  }

  public AttivitaPienaException() {
    super("Numero massimo di partecipanti raggiunto per questa attività.");
  }
}
