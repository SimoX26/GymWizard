package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.controller.app.AttivitaController;
import ispwproject.gymwizard.controller.DEMO.DemoFactory;
import ispwproject.gymwizard.model.Attivita;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.view.ListinoAttivitaView;

import java.util.List;

public class ListinoAttivitaCLIController {

    private final ListinoAttivitaView view = new ListinoAttivitaView();
    private final AttivitaController controller = DemoFactory.getAttivitaController();

    public CLIState start() {
        String ruolo = (String) SessionManager.getInstance().getAttributo("homePage");

        List<Attivita> attivitaList;
        try {
            attivitaList = controller.getAttivitaDisponibili(); // uso del controller dinamico
        } catch (DAOException e) {
            view.mostraMessaggio("❌ Errore nel caricamento delle attività: " + e.getMessage());
            view.attesaInvio();
            return CLIState.DASHBOARD_CLIENTE;
        }

        int scelta = view.mostraAttivita(attivitaList, ruolo.equalsIgnoreCase("Admin"));

        if (scelta == 0) {
            return CLIState.DASHBOARD_CLIENTE;
        } else if (scelta == -1 && ruolo.equalsIgnoreCase("Admin")) {
            return CLIState.CREA_ATTIVITA;
        }

        if (scelta >= 1 && scelta <= attivitaList.size()) {
            Attivita selezionata = attivitaList.get(scelta - 1); // Indice 1-based per CLI
            SessionManager.getInstance().setAttributo("attivitaSelezionata", selezionata);
            return new VisualizzaAttivitaCLIController().start(); // Avvia schermata dettagli
        }

        view.mostraMessaggio("❌ Scelta non valida.");
        view.attesaInvio();
        return CLIState.VISUALIZZA_ATTIVITA;
    }
}
