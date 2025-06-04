package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.util.singleton.SessionManager;

import java.util.Scanner;

public class DashboardAdminCLIController{

    private static final Scanner scanner = new Scanner(System.in);

    public static void start() {
        // Messaggio di benvenuto
        String username = SessionManager.getInstance().getSession().getUsername();
        System.out.println("\n👋 Benvenuto " + (username != null ? username : "admin") + "!");
        menu();
    }

    public static void menu() {
        while (true) {
            System.out.println("""
            \n🛠️ DASHBOARD AMMINISTRATORE:
            1. Gestione Listino Attività
            2. Visualizza Report e Statistiche
            3. Invia Comunicazioni
            4. Aiuto
            0. Logout
            """);

            System.out.print("👉 Scelta: ");
            String scelta = scanner.nextLine();

            switch (scelta) {
                case "1" -> onGestioneListino();
                case "2" -> onVisualizzaReport();
                case "3" -> onInviaComunicazioni();
                case "4" -> onHelp();
                case "0" -> {
                    onLogout();
                    return;
                }
                default -> System.out.println("❌ Scelta non valida.");
            }
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    private static void onGestioneListino() {
        System.out.println("\n📦 [GESTIONE LISTINO]");
        System.out.println("Funzionalità in sviluppo..."); // TODO: chiamare controller CLI specifico
        System.out.print("👉 Premi invio per tornare al menu: ");
        scanner.nextLine();
    }

    private static void onVisualizzaReport() {
        System.out.println("\n📊 [REPORT E STATISTICHE]");
        System.out.println("Funzionalità in sviluppo..."); // TODO: chiamare controller CLI specifico
        System.out.print("👉 Premi invio per tornare al menu: ");
        scanner.nextLine();
    }

    private static void onInviaComunicazioni() {
        System.out.println("\n📢 [INVIO COMUNICAZIONI]");
        System.out.println("Funzionalità in sviluppo..."); // TODO: chiamare controller CLI specifico
        System.out.print("👉 Premi invio per tornare al menu: ");
        scanner.nextLine();
    }

    private static void onHelp() {
        System.out.println("""
        🆘 Guida Amministratore:
        - Gestione Listino: Aggiungi, modifica o rimuovi attività.
        - Report: Statistiche su utenti e performance.
        - Comunicazioni: Invia messaggi globali ai clienti o ai coach.
        """);
        System.out.print("👉 Premi invio per tornare al menu: ");
        scanner.nextLine();
    }

    private static void onLogout() {
        System.out.println("🚪 Logout in corso...");
        SessionManager.getInstance().clearAll();
        System.out.println("✅ Logout effettuato. Ritorno al menu principale.");
    }
}


