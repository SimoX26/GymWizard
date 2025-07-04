package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.controller.app.SchedaController;
import ispwproject.gymwizard.model.Scheda;
import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.view.VisualizzaListaSchedeView;

import java.util.List;
import java.util.stream.Collectors;

public class VisualizzaListaSchedeCLIController {

    private final VisualizzaListaSchedeView view = new VisualizzaListaSchedeView();

    public CLIState start() {
        // Recupero l'utente loggato dalla sessione
        Object utenteObj = SessionManager.getInstance().getAttributo("utente");
        if (utenteObj == null) {
            view.mostraMessaggio("❌ Errore: utente non loggato.");
            view.attesaInvio();
            return CLIState.LOGIN; // o qualsiasi stato appropriato
        }

        int idUtente = ((ispwproject.gymwizard.model.Utente) utenteObj).getId();
        List<Scheda> listaSchede = SchedaController.getSchedeByIdCliente(idUtente);

        if (listaSchede.isEmpty()) {
            view.mostraMessaggio("⚠️ Nessuna scheda trovata per questo utente.");
            view.attesaInvio();

            String homePage = SessionManager.getInstance().getAttributo("homePage").toString();
            return homePage.equals("trainer") ? CLIState.LISTA_CLIENTI : CLIState.DASHBOARD_CLIENTE;
        }

        // Lista di nomi da visualizzare
        List<String> nomiSchede = listaSchede.stream()
                .map(Scheda::getNomeScheda)
                .collect(Collectors.toList());

        int scelta = view.scegliScheda(nomiSchede, false);

        if (scelta == -1) {
            String homePage = SessionManager.getInstance().getAttributo("homePage").toString();
            return homePage.equals("trainer") ? CLIState.LISTA_CLIENTI : CLIState.DASHBOARD_CLIENTE;
        }

        if (scelta == -2) {
            return CLIState.CREA_SCHEDA_CLIENTE;
        }

        if (scelta >= 0 && scelta < listaSchede.size()) {
            Scheda schedaSelezionata = listaSchede.get(scelta);
            SessionManager.getInstance().setAttributo("schedaSelezionata", schedaSelezionata);
            return CLIState.VISUALIZZA_ESERCIZI_SCHEDA;
        } else {
            view.mostraMessaggio("❌ Scelta non valida.");
            view.attesaInvio();
            return start();
        }
    }
}
