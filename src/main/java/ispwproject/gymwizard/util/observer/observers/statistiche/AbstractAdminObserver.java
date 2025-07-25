package ispwproject.gymwizard.util.observer.observers.statistiche;

import ispwproject.gymwizard.util.logger.AppLogger;
import ispwproject.gymwizard.util.observer.observers.Observer;
import ispwproject.gymwizard.util.observer.subjects.statistiche.ClienteSubject;

public abstract class AbstractAdminObserver extends Observer {

    protected ClienteSubject cliente;

    protected AbstractAdminObserver(long timeOut) {
        super(timeOut);
    }

    public void setSubject(ClienteSubject subject) {
        this.cliente = subject;
    }

    protected void log() {
        if (this.cliente != null) {
            AppLogger.getLogger().info("Log automatico: utente " + cliente.getUsername() +
                    " | Accesso: " + cliente.getUltimoAccesso() +
                    " | Stato abbonamento: " + (cliente.isAbbonamentoAttivo() ? "Attivo" : "Non attivo"));
        } else {
            notifySubjectStatus("Nessun ClienteSubject associato.");
        }
    }
}
