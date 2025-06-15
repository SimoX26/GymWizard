package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.util.singleton.SessionManager;

import java.util.Scanner;

public class DashboardTrainerCLIController {

    private static final Scanner scanner = new Scanner(System.in);

    public static void start() {
        // Messaggio di benvenuto
        String username = SessionManager.getInstance().getSession().getUsername();
        SessionManager.getInstance().setAttributo("homePage", "Trainer");
        System.out.println("\n👋 Benvenuto " + (username != null ? username : "trainer") + "!");
        menu();
    }

    private static void menu() {
        while (true) {
            System.out.println("""
            \n📋 DASHBOARD TRAINER:
            1. Lista Clienti
            2. Lista Chat
            3. Aiuto
            0. Logout
            """);

            System.out.print("👉 Scelta: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> switchToListaClienti();
                case "2" -> onChatListBtnClick();
                case "3" -> onHelpClick();
                case "0" -> {
                    onLogoutClick();
                    return;
                }
                default -> System.out.println("❌ Scelta non valida.");
            }
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    private static void switchToListaClienti() {
        System.out.println("📁 [Lista Clienti] Visualizzazione dei clienti associati...");
        // TODO: collegare alla logica dei clienti
    }

    private static void onChatListBtnClick() {
        System.out.println("💬 [Chat] Lista delle conversazioni disponibili...");
        // TODO: collegare alla logica della chat
    }

    private static void onHelpClick() {
        System.out.println("""
        🆘 Guida Interfaccia:
        Puoi accedere alla lista dei clienti oppure alla chat con loro.
        """);
    }

    private static void onLogoutClick() {
        System.out.println("🚪 Logout in corso...");
        SessionManager.getInstance().clearAll();
        System.out.println("✅ Logout effettuato. Ritorno al menu principale.");
    }
}

