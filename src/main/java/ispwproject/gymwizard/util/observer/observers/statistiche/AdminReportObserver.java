package ispwproject.gymwizard.util.observer.observers.statistiche;

import ispwproject.gymwizard.util.logger.AppLogger;
import ispwproject.gymwizard.util.observer.subjects.statistiche.ClienteSubject;

public class AdminReportObserver extends AbstractAdminObserver {

    public AdminReportObserver(long timeOut, ClienteSubject subject) {
        super(timeOut);
        this.cliente = subject;
    }

    @Override
    public void update() {
        if (this.cliente == null) {
            notifySubjectStatus("[ADMIN REPORT] Nessun ClienteSubject associato.");
            return;
        }

        log();

        AppLogger.getLogger().info("[ADMIN REPORT] Utente osservato: " + cliente.getUsername());
        AppLogger.getLogger().info(" - Ultimo accesso: " + cliente.getUltimoAccesso());
        AppLogger.getLogger().info(" - Abbonamento attivo: " + (cliente.isAbbonamentoAttivo() ? "Sì" : "No"));
    }
}
