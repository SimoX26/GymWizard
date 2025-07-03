package ispwproject.gymwizard.controller.cli;


import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.view.VisualizzaListaSchedeView;

import java.util.List;

public class VisualizzaListaSchedeCLIController {

    private final VisualizzaListaSchedeView view = new VisualizzaListaSchedeView();

    public CLIState start() {
        List<String> schedeDisponibili = getSchedePerUtenteLoggato();

        if (schedeDisponibili.isEmpty()) {
            view.mostraMessaggio("⚠️ Nessuna scheda trovata per questo utente.");
            view.attesaInvio();
            return CLIState.DASHBOARD_CLIENTE;
        }

        int scelta = view.scegliScheda(schedeDisponibili, false);

        if (scelta == -1) {
            return CLIState.DASHBOARD_CLIENTE;
        }

        String nomeSchedaSelezionata = schedeDisponibili.get(scelta);
        SessionManager.getInstance().setAttributo("schedaSelezionata", nomeSchedaSelezionata);

        return CLIState.VISUALIZZA_ESERCIZI_SCHEDA;
    }

    private List<String> getSchedePerUtenteLoggato() {
        // MOCK: In futuro usa DAO
        return List.of("Scheda Forza", "Scheda Ipertrofia", "Scheda Recupero");
    }
}
