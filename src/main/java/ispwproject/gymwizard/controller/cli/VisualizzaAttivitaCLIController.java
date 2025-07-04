package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.controller.app.AttivitaController;
import ispwproject.gymwizard.model.Attivita;
import ispwproject.gymwizard.util.exception.AttivitaPienaException;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.view.VisualizzaAttivitaView;

public class VisualizzaAttivitaCLIController {

    private final VisualizzaAttivitaView view = new VisualizzaAttivitaView();

    public CLIState start() {
        Attivita attivita = (Attivita) SessionManager.getInstance().getAttributo("attivitaSelezionata");
        view.mostraRiepilogo(attivita);

        String scelta = view.chiediConferma();

        if (scelta.equalsIgnoreCase("n")) {
            return CLIState.VISUALIZZA_ATTIVITA;
        }

        try {
            AttivitaController.prenotaAttivita(attivita);
            view.mostraMessaggio("✅ Prenotazione effettuata con successo!");
        } catch (AttivitaPienaException e) {
            view.mostraMessaggio("❌ Attività al completo. Impossibile prenotare.");
        } catch (DAOException e) {
            view.mostraMessaggio("❌ Errore durante la prenotazione: " + e.getMessage());
        }

        view.attesaInvio();
        return CLIState.VISUALIZZA_ATTIVITA;
    }
}


