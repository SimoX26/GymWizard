package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.view.CodiceAccessoView;


public class CodiceAccessoCLIController {

    private final CodiceAccessoView view = new CodiceAccessoView();

    public CLIState start() {
        view.mostraMessaggio("\nFunzionalità non implementata\n");
        view.attesaInvio();
        return CLIState.DASHBOARD_CLIENTE;
    }
}
