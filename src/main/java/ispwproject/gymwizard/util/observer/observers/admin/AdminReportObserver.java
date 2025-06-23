package ispwproject.gymwizard.util.observer.observers.admin;

import ispwproject.gymwizard.util.observer.subjects.cliente.ClienteSubject;

public class AdminReportObserver extends AbstractAdminObserver {

    @Override
    public void update(ClienteSubject cliente) {
        log(cliente);
        // Puoi inserire qui aggiornamento live GUI o salvataggio dati
        System.out.println("[ADMIN REPORT] Utente osservato: " + cliente.getUsername());
        System.out.println(" - Ultimo accesso: " + cliente.getUltimoAccesso());
        System.out.println(" - Abbonamento attivo: " + (cliente.isAbbonamentoAttivo() ? "Sì" : "No"));
    }
}
