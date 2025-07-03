package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.view.VisualizzaEserciziSchedaView;

public class VisualizzaEserciziSchedaCLIController {
    private final VisualizzaEserciziSchedaView view = new VisualizzaEserciziSchedaView();

    public void aggiungiEsercizio(String nomeScheda) {
        String esercizio = view.inserisciEsercizio();
        System.out.println("Esercizio aggiunto a \"" + nomeScheda + "\": " + esercizio);
        // Chiamare DAO/salvataggio reale
    }
}
