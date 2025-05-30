package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.util.singleton.SessionManager;

import java.util.Scanner;

public class DashboardAdminCLIController {
    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n🛠️ Dashboard Admin");
        System.out.println("1. Gestisci sistema");
        System.out.println("2. Logout");

        System.out.print("👉 Scelta: ");
        String choice = sc.nextLine();

        switch (choice) {
            case "1" -> {
                System.out.println("⚙️ Sistema funzionante.");
                start();
            }
            case "2" -> {
                SessionManager.getInstance().clearAll();
                System.out.println("🔒 Logout.");
                new LoginCLIController().start();
            }
            default -> {
                System.out.println("❌ Scelta non valida.");
                start();
            }
        }
    }
}
