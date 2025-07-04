package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.controller.app.SchedaController;
import ispwproject.gymwizard.model.Scheda;
import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.view.AggiungiEsercizioView;

public class AggiungiEsercizioCLIController {

    private final AggiungiEsercizioView view = new AggiungiEsercizioView();
    private final SchedaController controller = new SchedaController();

    public CLIState start() {
        Scheda scheda = (Scheda) SessionManager.getInstance().getAttributo("scheda");
        if (scheda == null) {
            view.mostraMessaggio("❌ Errore: nessuna scheda selezionata.");
            view.attesaInvio();
            return CLIState.VISUALIZZA_ESERCIZI_SCHEDA;
        }

        // Richiesta dati esercizio
        String nomeEsercizio = view.inserisciEsercizio(scheda.getNomeScheda());
        int serie = view.inserisciNumero("🔁 Numero di serie: ");
        int ripetizioni = view.inserisciNumero("📦 Ripetizioni per serie: ");
        String note = view.inserisciNote();

        if (nomeEsercizio == null || nomeEsercizio.isBlank()) {
            view.mostraMessaggio("❌ Nome esercizio non valido.");
        } else {
            try {
                controller.aggiungiEsercizio(nomeEsercizio, serie, ripetizioni, note);
                view.mostraMessaggio("✅ Esercizio \"" + nomeEsercizio + "\" aggiunto alla scheda \"" + scheda.getNomeScheda() + "\".");
            } catch (SchedaController.EsercizioDuplicatoException e) {
                view.mostraMessaggio("⚠️ " + e.getMessage());
            }
        }

        view.attesaInvio();
        return CLIState.VISUALIZZA_ESERCIZI_SCHEDA;
    }
}
