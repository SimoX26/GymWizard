package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.controller.app.LoginController;
import ispwproject.gymwizard.util.exception.AttivitaDuplicataException;
import ispwproject.gymwizard.util.exception.AttivitaPienaException;
import ispwproject.gymwizard.util.exception.CredenzialiException;
import ispwproject.gymwizard.util.exception.DAOException;

import java.sql.SQLException;
import java.util.Scanner;

public class LoginCLIController {

    private static final Scanner scanner = new Scanner(System.in);

    public static void start() {
        System.out.println("\n===== LOGIN GYM WIZARD CLI =====\n");

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (email.isEmpty() || password.isEmpty()) {
            System.out.println("❌ Inserisci sia email che password.");
            return;
        }

        try {
            LoginController loginController = new LoginController();
            LoginController.LoginResult result = loginController.login(email, password);

            switch (result) {
                case SUCCESSO_CLIENTE -> {
                    System.out.println("✅ Login effettuato! Benvenuto Cliente.");
                    DashboardClientCLIController.start();
                }
                case SUCCESSO_TRAINER -> {
                    System.out.println("✅ Login effettuato! Benvenuto Trainer.");
                    DashboardTrainerCLIController.start();
                }
                case SUCCESSO_ADMIN -> {
                    System.out.println("✅ Login effettuato! Benvenuto Admin.");
                    DashboardAdminCLIController.start();
                }
            }

        } catch (DAOException e) {
            System.out.println("❌ Errore di accesso al database: " + e.getMessage());
        } catch (AttivitaDuplicataException | AttivitaPienaException | SQLException | CredenzialiException e) {
            throw new RuntimeException(e);
        }
    }
}


