package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.view.CreaSchedaView;

public class CreaSchedaCLIController {
    private final CreaSchedaView view = new CreaSchedaView();
    public void creaScheda() {
        String nome = view.inserisciNomeScheda();
        System.out.println("Scheda \"" + nome + "\" creata con successo!");
        // DAO/salvataggio reale
    }
}
