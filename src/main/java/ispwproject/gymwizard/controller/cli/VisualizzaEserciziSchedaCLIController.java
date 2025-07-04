package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.controller.app.SchedaController;
import ispwproject.gymwizard.model.EsercizioScheda;
import ispwproject.gymwizard.model.Scheda;
import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.view.VisualizzaEserciziSchedaView;

import java.util.List;
import java.util.stream.Collectors;

public class VisualizzaEserciziSchedaCLIController {

    private final VisualizzaEserciziSchedaView view = new VisualizzaEserciziSchedaView();

    public CLIState start() {
        Scheda schedaSelezionata = (Scheda) SessionManager.getInstance().getAttributo("scheda");
        String ruolo = (String) SessionManager.getInstance().getAttributo("homePage"); // cliente | trainer | admin

        if (schedaSelezionata == null) {
            view.mostraMessaggio("⚠️ Nessuna scheda selezionata.");
            return CLIState.DASHBOARD_CLIENTE;
        }

        // ✅ Carica gli esercizi reali dal DB
        List<EsercizioScheda> eserciziScheda = SchedaController.getEserciziScheda(schedaSelezionata.getId());

        List<String> esercizi = eserciziScheda.stream()
                .map(e -> e.getNomeEsercizio() + " " + e.getSerie() + "x" + e.getRipetizioni())
                .collect(Collectors.toList());

        if (esercizi.isEmpty()) {
            esercizi.add("⚠️ Nessun esercizio presente in questa scheda.");
        }

        int scelta = view.mostraScheda(schedaSelezionata.getNomeScheda(), esercizi, ruolo.equals("trainer"));

        return switch (scelta) {
            case -1 -> ruolo.equals("trainer") ? CLIState.SELEZIONA_SCHEDA_CLIENTE : CLIState.SELEZIONA_SCHEDA;
            case -2 -> CLIState.AGGIUNGI_ESERCIZIO;
            case -3 -> {
                // In futuro: implementa svuotamento nel DB
                view.mostraMessaggio("⚠️ Funzione di svuotamento scheda non ancora implementata.");
                view.attesaInvio();
                yield CLIState.VISUALIZZA_ESERCIZI_SCHEDA;
            }
            default -> CLIState.VISUALIZZA_ESERCIZI_SCHEDA;
        };
    }
}
