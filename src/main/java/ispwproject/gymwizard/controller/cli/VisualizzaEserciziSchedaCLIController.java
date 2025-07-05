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
        String ruolo = (String) SessionManager.getInstance().getAttributo("homePage"); // "cliente", "trainer", "admin"

        if (schedaSelezionata == null) {
            view.mostraMessaggio("⚠️ Nessuna scheda selezionata.");
            view.attesaInvio();
            return ruolo.equalsIgnoreCase("Trainer") ? CLIState.DASHBOARD_TRAINER : CLIState.DASHBOARD_CLIENTE;
        }

        List<EsercizioScheda> eserciziScheda = SchedaController.getEserciziScheda(schedaSelezionata.getId());

        List<String> esercizi = eserciziScheda.stream()
                .map(e -> e.getNomeEsercizio() + " " + e.getSerie() + "x" + e.getRipetizioni())
                .collect(Collectors.toList());

        if (esercizi.isEmpty()) {
            esercizi.add("⚠️ Nessun esercizio presente in questa scheda.");
        }

        boolean isTrainer = ruolo.equalsIgnoreCase("Trainer");
        int scelta = view.mostraScheda(schedaSelezionata.getNomeScheda(), esercizi, isTrainer);

        return switch (scelta) {
            case -1 -> CLIState.SELEZIONA_SCHEDA;
            case -2 -> isTrainer ? CLIState.AGGIUNGI_ESERCIZIO : CLIState.VISUALIZZA_ESERCIZI_SCHEDA;
            case -3 -> {
                view.mostraMessaggio("⚠️ Funzione di svuotamento scheda non ancora implementata.");
                view.attesaInvio();
                yield CLIState.VISUALIZZA_ESERCIZI_SCHEDA;
            }
            default -> CLIState.VISUALIZZA_ESERCIZI_SCHEDA;
        };
    }
}
