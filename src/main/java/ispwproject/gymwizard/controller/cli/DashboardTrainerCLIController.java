package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.view.DashboardTrainerView;

public class DashboardTrainerCLIController {

    private final DashboardTrainerView view = new DashboardTrainerView();

    public CLIState start() {
        Utente u = (Utente) SessionManager.getInstance().getAttributo("utente");
        SessionManager.getInstance().setAttributo("homePage", "trainer");
        view.mostraBenvenuto(u.getUsername());

        return loopMenu();
    }

    private CLIState loopMenu() {
        while (true) {
            view.mostraMenu();
            String input = view.chiediScelta();

            switch (input) {
                case "1" -> {
                    return CLIState.LISTA_CLIENTI;
                }
                case "2" -> {
                    return CLIState.CHAT;
                }
                case "3" -> view.mostraAiuto();
                case "0" -> {
                    onLogoutClick();
                    return CLIState.LOGIN;
                }
                default -> view.mostraMessaggio("❌ Scelta non valida.");
            }

            view.pulisciSchermo();
        }
    }

    private void onLogoutClick() {
        view.mostraMessaggio("🚪 Logout in corso...");
        SessionManager.getInstance().clearAll();
        view.mostraMessaggio("✅ Logout effettuato. Ritorno al menu principale.");
    }
}
