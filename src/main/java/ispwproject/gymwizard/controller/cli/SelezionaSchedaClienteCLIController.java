package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.controller.app.SchedaController;
import ispwproject.gymwizard.model.Scheda;
import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.view.VisualizzaListaSchedeView;

import java.util.List;
import java.util.stream.Collectors;

public class SelezionaSchedaClienteCLIController {

    private final VisualizzaListaSchedeView view = new VisualizzaListaSchedeView();

    public CLIState start() {
        // Recupera il cliente selezionato dalla sessione
        Utente cliente = (Utente) SessionManager.getInstance().getAttributo("clienteSelezionato");

        // Carica le schede dal DB usando il controller reale
        List<Scheda> listaSchede = SchedaController.getSchedeByIdCliente(cliente.getId());

        if (listaSchede == null || listaSchede.isEmpty()) {
            view.mostraMessaggio("⚠️ Nessuna scheda trovata per " + cliente.getUsername());
            return CLIState.CREA_SCHEDA_CLIENTE;
        }

        // Estrai i nomi delle schede da visualizzare
        List<String> nomiSchede = listaSchede.stream()
                .map(Scheda::getNomeScheda)
                .collect(Collectors.toList());

        int scelta = view.scegliScheda(nomiSchede, true); // true: mostra "Crea nuova"

        if (scelta == -1) {
            return CLIState.LISTA_CLIENTI;
        } else if (scelta == -2) {
            return CLIState.CREA_SCHEDA_CLIENTE;
        }

        // Salva la scheda selezionata nel SessionManager
        Scheda schedaSelezionata = listaSchede.get(scelta);
        SessionManager.getInstance().setAttributo("schedaSelezionata", schedaSelezionata);

        return CLIState.VISUALIZZA_ESERCIZI_SCHEDA;
    }
}