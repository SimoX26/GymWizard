package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.controller.app.LoginController;
import ispwproject.gymwizard.controller.demo.DemoFactory;
import ispwproject.gymwizard.util.exception.CredenzialiException;
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
            // Usa controller dinamico
            LoginController loginController = DemoFactory.getLoginController();
            LoginController.LoginResult result = loginController.login(email, password);

            if (result == null) {
                System.out.println("❌ Login fallito: credenziali non valide.");
                return CLIState.LOGIN;
            }

            view.mostraSuccesso();

            return switch (result) {
                case SUCCESSO_CLIENTE -> CLIState.DASHBOARD_CLIENTE;
                case SUCCESSO_TRAINER -> CLIState.DASHBOARD_TRAINER;
                case SUCCESSO_ADMIN -> CLIState.DASHBOARD_ADMIN;
            };

        } catch (SQLException | CredenzialiException e) {
            System.out.println("❌ Login fallito: " + e.getMessage());
            return CLIState.LOGIN;
        }
    }
}
