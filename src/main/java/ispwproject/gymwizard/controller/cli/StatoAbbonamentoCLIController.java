package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.controller.app.AbbonamentoController;
import ispwproject.gymwizard.controller.demo.DemoFactory;
import ispwproject.gymwizard.model.Abbonamento;
import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.view.StatoAbbonamentoView;

public class StatoAbbonamentoCLIController {

    private final StatoAbbonamentoView view = new StatoAbbonamentoView();

    // Controller dinamico (DEMO o DBMS)
    private final AbbonamentoController controller = DemoFactory.getAbbonamentoController();

    public CLIState start() {
        Utente utente = (Utente) SessionManager.getInstance().getAttributo("utente");

        if (utente == null) {
            view.mostraMessaggio("❌ Nessun utente loggato.");
            view.attesaInvio();
            return CLIState.LOGIN;
        }


        Abbonamento abbonamento = controller.getDatiAbbonamento();


        int scelta = view.mostraAbbonamento(abbonamento);

        return switch (scelta) {
            case 1 -> CLIState.RINNOVA_ABBONAMENTO;
            case 0 -> CLIState.DASHBOARD_CLIENTE;
            default -> CLIState.DASHBOARD_CLIENTE;
        };
    }
}
