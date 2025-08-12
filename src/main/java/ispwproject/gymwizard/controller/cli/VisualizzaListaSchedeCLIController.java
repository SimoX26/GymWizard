package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.controller.app.SchedaController;
import ispwproject.gymwizard.controller.demo.DemoFactory;
import ispwproject.gymwizard.model.Scheda;
import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.bean.SchedaBean;
import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.view.VisualizzaListaSchedeView;

import java.util.List;

public class VisualizzaListaSchedeCLIController {

    // Controller dinamico (può essere demo o reale)
    private final SchedaController controller;

    private final SchedaBean bean = new SchedaBean();

    public VisualizzaListaSchedeCLIController() {
        // Sostituisci questo con: new SchedaController() se vuoi forzare l'uso del controller reale
        this.controller = DemoFactory.getSchedaController();
    }

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

        controller.getSchedeByIdCliente(bean, idUtente);
        List<Scheda> schede = bean.getListaSchede();
        List<String> nomiSchede = schede.stream().map(Scheda::getNomeScheda).toList();

        boolean mostraCrea = ruolo.equalsIgnoreCase("Trainer");
        int scelta = view.scegliScheda(nomiSchede, mostraCrea);

        if (scelta == -1) {
            return mostraCrea ? CLIState.DASHBOARD_TRAINER : CLIState.DASHBOARD_CLIENTE;
        } else if (scelta == -2 && mostraCrea) {
            return CLIState.CREA_SCHEDA_CLIENTE;
        }

        Scheda selezionata = schede.get(scelta);
        SessionManager.getInstance().setAttributo("scheda", selezionata);
        return CLIState.VISUALIZZA_ESERCIZI_SCHEDA;
    }
}
