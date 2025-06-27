package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.util.exception.AttivitaDuplicataException;
import ispwproject.gymwizard.util.exception.AttivitaPienaException;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.singleton.SessionManager;

import java.util.Scanner;

public class DashboardClientCLIController {

    private static final Scanner scanner = new Scanner(System.in);

    public static void start() throws DAOException, AttivitaDuplicataException, AttivitaPienaException {
        // Messaggio di benvenuto
        String username = SessionManager.getInstance().getSession().getUsername();
        SessionManager.getInstance().setAttributo("homePage", "Cliente");
        System.out.println("\n👋 Benvenuto " + (username != null ? username : "cliente") + "!");
        menu();
    }

    private static void menu() throws DAOException, AttivitaDuplicataException, AttivitaPienaException {
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

    private static void onScheda() {
        new SchedaAllenamentoCLIController().start();
    }

    private static void onAttivita() throws DAOException, AttivitaDuplicataException, AttivitaPienaException {
        new AttivitaCLIController().start();
    }

    private static void onStatoAbbonamento() {
        new StatoAbbonamentoCLIController().start();
    }

    private static void onListaChat() {
        new ListaChatCLIController().start();
    }

    private static void onCodiceAccesso() {
        new CodiceAccessoCLIController().start();
    }

    private static void onHelp() {
        System.out.println("""
        🆘 Guida Interfaccia:
        - Scheda Allenamento: Visualizza la scheda di allenamento assegnata.
        - Lista Attività: Visualizza la lista delle attività disponibili in modo da prenotarsi.
        - Stato Abbonamento: Visualizza lo stato di abbonamento ed eventualmente effettuare un rinnovo.
        - Lista Chat: Visuliazza la lista delle chat con i trainer.
        - Codice D'Accesso: Visualizza il codice QR per accedere alla struttura
        """);
    }

    private static void onLogout() {
        System.out.println("🚪 Logout in corso...");
        SessionManager.getInstance().clearAll();
        System.out.println("✅ Logout effettuato. Ritorno al menu principale.");
    }
}

