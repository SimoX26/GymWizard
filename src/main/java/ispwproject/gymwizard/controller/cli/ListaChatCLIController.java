package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.view.ListaClientiView;


public class ListaChatCLIController{

    private final ListaClientiView view = new ListaClientiView();

    public CLIState start() {
        view.mostraMessaggio("\nFunzionalità non implementata\n");
        view.attesaInvio();

        String s = (String) SessionManager.getInstance().getAttributo("homePage");

        if(s.equals("cliente")){
            return CLIState.DASHBOARD_CLIENTE;
        } else {
            return CLIState.DASHBOARD_TRAINER;
        }
    }
}
