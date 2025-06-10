package ispwproject.gymwizard.util.observer.observers.admin;

import ispwproject.gymwizard.util.observer.subjects.cliente.ClienteSubject;

public class AdminReportObserver implements Observer {

    @Override
    public void update(ClienteSubject cliente) {
        System.out.println("[ADMIN] Report aggiornato:");
        System.out.println(" - Cliente: " + cliente.getUsername());
        System.out.println(" - Peso attuale: " + cliente.getPeso() + " kg");
        System.out.println(" - Abbonamento attivo: " + (cliente.isAbbonamentoAttivo() ? "Sì" : "No"));
    }
}
