package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.exception.AttivitaDuplicataException;
import ispwproject.gymwizard.util.exception.AttivitaPienaException;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.view.DashboardClienteView;

public class DashboardClienteCLIController {

    private final DashboardClienteView view = new DashboardClienteView();

    public CLIState start() throws DAOException, AttivitaDuplicataException, AttivitaPienaException {
        Utente u = (Utente) SessionManager.getInstance().getAttributo("utente");
        SessionManager.getInstance().setAttributo("homePage", "cliente");
        view.mostraBenvenuto(u.getUsername());

        return loopMenu();
    }

    private CLIState loopMenu() {
        while (true) {
            view.mostraMenu();
            String scelta = view.chiediScelta();

            switch (scelta) {
                case "1" -> {
                    return CLIState.SELEZIONA_SCHEDA;
                }
                case "2" -> {
                    return CLIState.LISTINO_ATTIVITA;
                }
                case "3" -> {
                    return CLIState.GESTIONE_ABBONAMENTI;
                }
                case "4" -> {
                    return CLIState.CHAT;
                }
                case "5" -> {
                    return CLIState.CODICE_ACCESSO;
                }
                case "6" -> view.mostraAiuto();
                case "0" -> {
                    onLogout();
                    return CLIState.LOGIN;
                }
                default -> view.mostraMessaggio("❌ Scelta non valida.");
            }

            view.pulisciSchermo();
        }
    }

    private void onLogout() {
        view.mostraMessaggio("🚪 Logout in corso...");
        SessionManager.getInstance().clearAll();
        view.mostraMessaggio("✅ Logout effettuato. Ritorno al menu principale.");
    }
}
