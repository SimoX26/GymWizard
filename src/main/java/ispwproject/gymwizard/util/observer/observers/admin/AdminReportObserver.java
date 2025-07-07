package ispwproject.gymwizard.util.observer.observers.admin;

import ispwproject.gymwizard.util.logger.AppLogger;
import ispwproject.gymwizard.util.observer.subjects.cliente.ClienteSubject;

public class AdminReportObserver extends AbstractAdminObserver {

    @Override
    public void update(ClienteSubject cliente) {
        log(cliente);

        AppLogger.getLogger().info("[ADMIN REPORT] Utente osservato: " + cliente.getUsername());
        AppLogger.getLogger().info(" - Ultimo accesso: " + cliente.getUltimoAccesso());
        AppLogger.getLogger().info(" - Abbonamento attivo: " + (cliente.isAbbonamentoAttivo() ? "Sì" : "No"));
    }
}
