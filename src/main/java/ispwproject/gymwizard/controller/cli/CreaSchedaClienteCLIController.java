package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.view.CreaSchedaClienteView;

public class CreaSchedaClienteCLIController {

    private final CreaSchedaClienteView view = new CreaSchedaClienteView();

    public CLIState start() {
        String cliente = (String) SessionManager.getInstance().getAttributo("clienteSelezionato");

        String nomeScheda = view.chiediNomeScheda(cliente);
        if (nomeScheda == null || nomeScheda.isBlank()) {
            view.mostraMessaggio("❌ Nome scheda non valido.");
            view.attesaInvio();
            return CLIState.SELEZIONA_SCHEDA_CLIENTE;
        }

        // MOCK – in futuro inserire nel DB
        SessionManager.getInstance().setAttributo("schedaSelezionata", nomeScheda);
        view.mostraMessaggio("✅ Scheda \"" + nomeScheda + "\" creata per " + cliente + ".");
        view.attesaInvio();

        return CLIState.VISUALIZZA_ESERCIZI_SCHEDA;
    }
}
