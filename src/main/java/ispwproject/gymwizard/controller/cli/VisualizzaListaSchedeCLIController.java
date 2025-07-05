package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.controller.app.SchedaController;
import ispwproject.gymwizard.model.Scheda;
import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.view.VisualizzaListaSchedeView;

import java.util.List;

public class VisualizzaListaSchedeCLIController {

    public CLIState start() {
        VisualizzaListaSchedeView view = new VisualizzaListaSchedeView();
        String ruolo = (String) SessionManager.getInstance().getAttributo("homePage");

        int idUtente;
        if (ruolo.equalsIgnoreCase("Trainer")) {
            Utente selezionato = (Utente) SessionManager.getInstance().getAttributo("clienteSelezionato");
            if (selezionato == null) {
                view.mostraMessaggio("❌ Nessun cliente selezionato.");
                view.attesaInvio();
                return CLIState.DASHBOARD_TRAINER;
            }
            idUtente = selezionato.getId();
        } else {
            Utente utente = (Utente) SessionManager.getInstance().getAttributo("utente");
            idUtente = utente.getId();
        }

        List<Scheda> schede = SchedaController.getSchedeByIdCliente(idUtente);
        List<String> nomiSchede = schede.stream().map(Scheda::getNomeScheda).toList();

        boolean mostraCrea = ruolo.equalsIgnoreCase("Trainer");
        int scelta = view.scegliScheda(nomiSchede, mostraCrea);

        if (scelta == -1) {
            return ruolo.equalsIgnoreCase("Trainer") ? CLIState.DASHBOARD_TRAINER : CLIState.DASHBOARD_CLIENTE;
        } else if (scelta == -2 && mostraCrea) {
            return CLIState.CREA_SCHEDA_CLIENTE;
        }

        Scheda selezionata = schede.get(scelta);
        SessionManager.getInstance().setAttributo("scheda", selezionata);
        return CLIState.VISUALIZZA_ESERCIZI_SCHEDA;
    }
}
