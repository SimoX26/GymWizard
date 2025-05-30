package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.util.singleton.SessionManager;

import java.util.Scanner;

public class DashboardClientCLIController {

    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        // Messaggio di benvenuto
        String username = SessionManager.getInstance().getSession().getUsername();
        System.out.println("\n👋 Benvenuto " + (username != null ? username : "utente") + "!");
        menu();
    }

    private void menu() {
        while (true) {
            System.out.println("\n📋 DASHBOARD CLIENTE:");
            System.out.println("1. Visualizza Scheda Allenamento");
            System.out.println("2. Visualizza Attività");
            System.out.println("3. Stato Abbonamento");
            System.out.println("4. Lista Chat");
            System.out.println("5. Codice Accesso");
            System.out.println("6. Aiuto");
            System.out.println("0. Logout");

            System.out.print("👉 Scelta: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> onScheda();
                case "2" -> onAttivita();
                case "3" -> onStatoAbbonamento();
                case "4" -> onListaChat();
                case "5" -> onCodiceAccesso();
                case "6" -> onHelp();
                case "0" -> {
                    onLogout();
                    return;
                }
                default -> System.out.println("❌ Scelta non valida.");
            }
        }
    }

    private void onScheda() {
        // System.out.println("📘 [Scheda Allenamento] Mostra dettagli allenamento...");
        new SchedaAllenamentoCLIController().start();
    }

    private void onAttivita() {
        // System.out.println("🏃 [Attività] Elenco attività disponibili...");
        new AttivitaCLIController().start();
    }

    private void onStatoAbbonamento() {
        // System.out.println("📄 [Stato Abbonamento] Mostra stato abbonamento...");
        new StatoAbbonamentoCLIController().start();

    }

    private void onListaChat() {
        // System.out.println("💬 [Chat] Mostra chat disponibili...");
        new ListaChatCLIController().start();
    }

    private void onCodiceAccesso() {
        // System.out.println("🔐 [Codice Accesso] Codice generato o attivo: ABC123");
        new CodiceAccessoCLIController().start();
    }

    private void onHelp() {
        System.out.println("🆘 Guida Interfaccia:");
        System.out.println("Puoi accedere alla scheda allenamento, attività, chat e codice accesso.");
    }

    private void onLogout() {
        System.out.println("🚪 Logout in corso...");
        SessionManager.getInstance().clearAll();
        System.out.println("✅ Logout effettuato. Ritorno al menu principale.");
    }
}

