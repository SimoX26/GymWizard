package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.controller.app.LoginController;
import ispwproject.gymwizard.util.exception.CredenzialiException;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.view.LoginView;

import java.sql.SQLException;

public class LoginCLIController {

    private final LoginView view = new LoginView();

    public CLIState start() {
        String[] credenziali = view.chiediCredenziali();
        String email = credenziali[0];
        String password = credenziali[1];

        if (email.isEmpty() || password.isEmpty()) {
            view.mostraErroreInput();
            return CLIState.LOGIN;
        }

        try {
            LoginController loginController = new LoginController();
            LoginController.LoginResult result = loginController.login(email, password);

            return switch (result) {
                case SUCCESSO_CLIENTE -> {
                    view.mostraSuccesso();
                    yield CLIState.DASHBOARD_CLIENTE;
                }
                case SUCCESSO_TRAINER -> {
                    view.mostraSuccesso();
                    yield CLIState.DASHBOARD_TRAINER;
                }
                case SUCCESSO_ADMIN -> {
                    view.mostraSuccesso();
                    yield CLIState.DASHBOARD_ADMIN;
                }
            };

        } catch (DAOException e) {
            view.mostraErroreDB(e.getMessage());
            return CLIState.LOGIN;
        } catch (SQLException | CredenzialiException e) {
            System.out.println("❌ Login fallito: " + e.getMessage());
            return CLIState.LOGIN; // torna alla schermata di login
        }
    }
}


