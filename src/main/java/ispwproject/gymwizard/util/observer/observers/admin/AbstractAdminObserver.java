package ispwproject.gymwizard.util.observer.observers.admin;

import ispwproject.gymwizard.util.logger.AppLogger;
import ispwproject.gymwizard.util.observer.subjects.cliente.ClienteSubject;

public abstract class AbstractAdminObserver implements Observer {

    protected void log(ClienteSubject cliente) {
        AppLogger.getLogger().info("Log automatico: utente " + cliente.getUsername() +
                " | Accesso: " + cliente.getUltimoAccesso() +
                " | Stato abbonamento: " + (cliente.isAbbonamentoAttivo() ? "Attivo" : "Non attivo"));
    }
}
