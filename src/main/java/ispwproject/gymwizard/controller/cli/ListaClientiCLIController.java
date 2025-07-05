package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.controller.app.ClientiController;
import ispwproject.gymwizard.controller.demo.DemoFactory;
import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.view.ListaClientiView;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ListaClientiCLIController {

    private final ListaClientiView view = new ListaClientiView();
    private final ClientiController clientiController = DemoFactory.getClientiController(); // ✅ dynamic controller

    public CLIState start() {
        List<Utente> clienti;

        try {
            clienti = clientiController.getClienti();
        } catch (SQLException e) {
            view.mostraMessaggio("❌ Errore durante il caricamento dei clienti: " + e.getMessage());
            view.attesaInvio();
            return CLIState.DASHBOARD_TRAINER;
        }

        if (clienti.isEmpty()) {
            view.mostraMessaggio("⚠️ Nessun cliente trovato nel sistema.");
            view.attesaInvio();
            return CLIState.DASHBOARD_TRAINER;
        }

        List<String> nomiClienti = clienti.stream()
                .map(Utente::getUsername)
                .collect(Collectors.toList());

        int scelta = view.selezionaCliente(nomiClienti);

        if (scelta == 0) {
            return CLIState.DASHBOARD_TRAINER;
        }

        if (scelta < 1 || scelta > clienti.size()) {
            view.mostraMessaggio("❌ Scelta non valida.");
            view.attesaInvio();
            return CLIState.LISTA_CLIENTI;
        }

        Utente clienteSelezionato = clienti.get(scelta - 1);
        SessionManager.getInstance().setAttributo("clienteSelezionato", clienteSelezionato);

        view.mostraMessaggio("✅ Hai selezionato: " + clienteSelezionato.getUsername() + "\n");

        return CLIState.SELEZIONA_SCHEDA;
    }
}
