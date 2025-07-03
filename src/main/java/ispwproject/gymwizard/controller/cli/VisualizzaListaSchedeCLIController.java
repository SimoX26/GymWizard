package ispwproject.gymwizard.controller.cli;


import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.view.VisualizzaListaSchedeView;

import java.util.List;

public class VisualizzaListaSchedeCLIController {

    private final VisualizzaListaSchedeView view = new VisualizzaListaSchedeView();

    public CLIState start() {
        List<String> schedeDisponibili = getSchedePerUtenteLoggato();

        if (schedeDisponibili.isEmpty()) {
            view.mostraMessaggio("⚠️ Nessuna scheda trovata per questo utente.");
            view.attesaInvio();
            return CLIState.DASHBOARD_CLIENTE;
        }

        int scelta = view.scegliScheda(schedeDisponibili, false);

        if (scelta == -1) {
            String homePage = SessionManager.getInstance().getAttributo("homePage").toString();
            return homePage.equals("trainer") ? CLIState.LISTA_CLIENTI : CLIState.DASHBOARD_CLIENTE;
        }

        if (scelta == -2) {
            return CLIState.CREA_SCHEDA_CLIENTE; // solo se `mostraCreaScheda = true`
        }

        // ✅ Sicuro di essere in un range valido
        if (scelta >= 0 && scelta < schedeDisponibili.size()) {
            String nomeSchedaSelezionata = schedeDisponibili.get(scelta);
            SessionManager.getInstance().setAttributo("schedaSelezionata", nomeSchedaSelezionata);
            return CLIState.VISUALIZZA_ESERCIZI_SCHEDA;
        } else {
            view.mostraMessaggio("❌ Scelta non valida.");
            view.attesaInvio();
            return start(); // ricomincia la schermata
        }
    }


    private List<String> getSchedePerUtenteLoggato() {
        // MOCK: In futuro usa DAO
        return List.of("Scheda Forza", "Scheda Ipertrofia", "Scheda Recupero");
    }
}
