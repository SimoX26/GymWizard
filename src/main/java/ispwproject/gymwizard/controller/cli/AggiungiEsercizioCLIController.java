package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.view.AggiungiEsercizioView;

public class AggiungiEsercizioCLIController {

    private final AggiungiEsercizioView view = new AggiungiEsercizioView();

    public CLIState start() {
        String scheda = (String) SessionManager.getInstance().getAttributo("schedaSelezionata");
        String esercizio = view.inserisciEsercizio(scheda);

        if (esercizio == null || esercizio.isBlank()) {
            view.mostraMessaggio("❌ Esercizio non valido.");
        } else {
            view.mostraMessaggio("✅ Esercizio \"" + esercizio + "\" aggiunto alla scheda \"" + scheda + "\".");
            // In futuro: salva nel DB
        }

        view.attesaInvio();
        return CLIState.VISUALIZZA_ESERCIZI_SCHEDA;
    }
}
