package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.util.exception.AttivitaDuplicataException;
import ispwproject.gymwizard.util.exception.AttivitaPienaException;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.view.DashboardAdminView;

public class DashboardAdminCLIController {

    private final DashboardAdminView view = new DashboardAdminView();

    public CLIState start() throws DAOException, AttivitaDuplicataException, AttivitaPienaException {
        String username = SessionManager.getInstance().getAttributo("utente").toString();
        SessionManager.getInstance().setAttributo("homePage", "admin");
        view.mostraBenvenuto(username);

        return loopMenu();
    }

    private CLIState loopMenu() throws AttivitaDuplicataException, AttivitaPienaException {
        while (true) {
            view.mostraMenu();
            String scelta = view.chiediScelta();

            switch (scelta) {
                case "1" -> {
                    return CLIState.ATTIVITA;
                }
                case "2" -> {
                    mostraFunzioneInSviluppo("📊 [REPORT E STATISTICHE]");
                    continue;
                }
                case "3" -> {
                    mostraFunzioneInSviluppo("📢 [INVIO COMUNICAZIONI]");
                    continue;
                }
                case "4" -> {
                    view.mostraHelp();
                    continue;
                }
                case "0" -> {
                    onLogout();
                    return CLIState.LOGIN;
                }
                default -> view.mostraMessaggio("❌ Scelta non valida.");
            }

            view.pulisciSchermo();
        }
    }

    private void mostraFunzioneInSviluppo(String titolo) {
        view.mostraMessaggio("\n" + titolo);
        view.mostraMessaggio("Funzionalità in sviluppo...");
        view.attesaInvioPerContinuare();
    }

    private void onLogout() {
        view.mostraMessaggio("🚪 Logout in corso...");
        SessionManager.getInstance().clearAll();
        view.mostraMessaggio("✅ Logout effettuato. Ritorno al menu principale.");
    }
}
