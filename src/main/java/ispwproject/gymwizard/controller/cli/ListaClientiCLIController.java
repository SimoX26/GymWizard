package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.view.ListaClientiView;

import java.util.List;

public class ListaClientiCLIController {

    private final ListaClientiView view = new ListaClientiView();

    public CLIState start() {
        String trainer = SessionManager.getInstance().getSession().getUsername();
        List<String> clienti = getClientiPresentiPerTrainer(trainer);

        if (clienti.isEmpty()) {
            view.mostraMessaggio("❌ Nessun cliente disponibile.");
            view.attesaInvio();
            return CLIState.DASHBOARD_TRAINER;
        }

        int scelta = view.selezionaCliente(clienti);
        if (scelta == -1) {
            return CLIState.DASHBOARD_TRAINER;
        }

        String clienteSelezionato = clienti.get(scelta);
        SessionManager.getInstance().setAttributo("clienteSelezionato", clienteSelezionato);

        return CLIState.SELEZIONA_SCHEDA_CLIENTE;
    }

    private List<String> getClientiPresentiPerTrainer(String trainerUsername) {
        return List.of("Mario Rossi", "Giulia Verdi", "Luca Bianchi");
    }
}
