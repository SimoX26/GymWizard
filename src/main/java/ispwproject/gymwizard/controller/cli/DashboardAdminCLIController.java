package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.util.singleton.SessionManager;

import java.util.Scanner;

public class DashboardAdminCLIController {

    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        // Messaggio di benvenuto
        String username = SessionManager.getInstance().getSession().getUsername();
        System.out.println("\n👋 Benvenuto amministratore" + (username != null ? ", " + username : "") + "!");
        menu();
    }

    private void menu() {
        while (true) {
            System.out.println("\n📋 DASHBOARD AMMINISTRATORE:");
            System.out.println("1. Gestione Listino Attività");
            System.out.println("2. Report e Statistiche");
            System.out.println("3. Invia Comunicazioni");
            System.out.println("4. Aiuto");
            System.out.println("0. Logout");

            System.out.print("👉 Scelta: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> gestioneListino();
                case "2" -> visualizzaReport();
                case "3" -> inviaComunicazioni();
                case "4" -> mostraGuida();
                case "0" -> {
                    logout();
                    return;
                }
                default -> System.out.println("❌ Scelta non valida.");
            }
        }
    }

    private void gestioneListino() {
        System.out.println("📋 [Listino] Gestione delle attività disponibili...");
        // TODO: integrare logica di business
    }

    private void visualizzaReport() {
        System.out.println("📈 [Report] Visualizzazione statistiche...");
        // TODO: integrare logica di business
    }

    private void inviaComunicazioni() {
        System.out.println("📢 [Comunicazioni] Invio messaggi agli utenti...");
        // TODO: integrare logica di business
    }

    private void mostraGuida() {
        System.out.println("""
        🆘 Guida Interfaccia Amministratore:
        - Gestione Listino: modifica attività disponibili
        - Report: consulta statistiche della palestra
        - Comunicazioni: invia messaggi globali agli utenti
        """);
    }

    private void logout() {
        System.out.println("🚪 Logout in corso...");
        SessionManager.getInstance().clearAll();
        System.out.println("✅ Logout effettuato. Ritorno al menu principale.");
    }
}

