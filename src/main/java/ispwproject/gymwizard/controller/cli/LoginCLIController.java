package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.controller.app.LoginController;
import ispwproject.gymwizard.util.exception.DAOException;

import java.util.Scanner;

public class LoginCLIController {

    private static Scanner scanner;

    public LoginCLIController() {
        scanner = new Scanner(System.in); // ✅ inizializzazione corretta
    }

    public static void start() {
        System.out.println("===== LOGIN GYM WIZARD CLI =====");

        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

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
                case CREDENZIALI_INVALIDE -> System.out.println("❌ Credenziali non valide.");
                case ERRORE -> System.out.println("❌ Errore imprevisto durante il login.");
            }

        } catch (DAOException e) {
            System.out.println("❌ Errore di accesso al database: " + e.getMessage());
        }
    }
}


