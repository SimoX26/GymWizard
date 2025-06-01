package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.util.singleton.SessionManager;

import java.util.Scanner;

public class DashboardClientCLIController {

    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        // Messaggio di benvenuto
        String username = SessionManager.getInstance().getSession().getUsername();
        System.out.println("\n👋 Benvenuto " + (username != null ? username : "cliente") + "!");
        menu();
    }

    private void menu() {
        while (true) {
            System.out.println("""
            \n📋 DASHBOARD CLIENTE:
            1. Scheda Allenamento
            2. Lista Attività
            3. Stato Abbonamento
            4. Lista Chat
            5. Codice Accesso
            6. Aiuto
            0. Logout
            """);

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
            for (int i = 0; i < 50; i++) {
                System.out.println();
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
        System.out.println("""
        🆘 Guida Interfaccia:
        - Scheda Allenamento: Visualizza la scheda di allenamento assegnata.
        - Lista Attività: Visualizza la lista delle attività disponibili in modo da prenotarsi.
        - Stato Abbonamento: Visualizza lo stato di abbonamento ed eventualmente effettuare un rinnovo.
        - Lista Chat: Visuliazza la lista delle chat con i trainer.
        - Codice D'Accesso: Visualizza il codice QR per accedere alla struttura
        """);
    }

    private void onLogout() {
        System.out.println("🚪 Logout in corso...");
        SessionManager.getInstance().clearAll();
        System.out.println("✅ Logout effettuato. Ritorno al menu principale.");
    }
}

