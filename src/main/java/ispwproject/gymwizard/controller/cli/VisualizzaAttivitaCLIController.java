package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.controller.app.AttivitaController;
import ispwproject.gymwizard.controller.DEMO.DemoFactory;
import ispwproject.gymwizard.model.Attivita;
import ispwproject.gymwizard.util.exception.AttivitaPienaException;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.view.VisualizzaAttivitaView;

public class VisualizzaAttivitaCLIController {

    private final VisualizzaAttivitaView view = new VisualizzaAttivitaView();
    private final AttivitaController controller = DemoFactory.getAttivitaController(); // ✅ Controller dinamico

    public CLIState start() {
        Attivita attivita = (Attivita) SessionManager.getInstance().getAttributo("attivitaSelezionata");
        String ruolo = (String) SessionManager.getInstance().getAttributo("homePage");

        if (attivita == null) {
            view.mostraMessaggio("❌ Nessuna attività selezionata.");
            view.attesaInvio();
            return CLIState.LISTINO_ATTIVITA;
        }

        view.mostraRiepilogo(attivita);

        // Se Admin: niente conferma prenotazione
        if ("Admin".equalsIgnoreCase(ruolo)) {
            view.mostraMessaggio("👤 Sei un amministratore. Non puoi prenotare un'attività.");
            view.attesaInvio();
            return CLIState.LISTINO_ATTIVITA;
        }

        // Cliente normale → può prenotare
        String scelta = view.chiediConferma(); // Attende 's' o 'n'

        if (scelta.equalsIgnoreCase("n")) {
            view.mostraMessaggio("❌ Prenotazione annullata.");
            view.attesaInvio();
            return CLIState.LISTINO_ATTIVITA;
        }

        try {
            controller.prenotaAttivita(attivita); // ✅ uso del controller dinamico
            view.mostraMessaggio("✅ Prenotazione effettuata con successo!");
        } catch (AttivitaPienaException e) {
            view.mostraMessaggio("❌ Attività al completo. Impossibile prenotare.");
        } catch (DAOException e) {
            view.mostraMessaggio("❌ Errore durante la prenotazione: " + e.getMessage());
        }

        view.attesaInvio();
        return CLIState.LISTINO_ATTIVITA;
    }
}
