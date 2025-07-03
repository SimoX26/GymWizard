package ispwproject.gymwizard.view;

import java.util.Scanner;

public class DashboardClienteView {
    private final Scanner scanner = new Scanner(System.in);

    public void mostraBenvenuto(String username) {
        System.out.println("\n👋 Benvenuto " + (username != null ? username : "cliente") + "!");
    }

    public void mostraMenu() {
        System.out.println("""
            \n📋 DASHBOARD CLIENTE:
            1. Visualizza scheda allenamento
            2. Listino attività
            3. Stato abbonamento
            4. Chat
            5. Codice d'accesso
            6. Aiuto
            0. Logout
        """);
    }

    public String chiediScelta() {
        System.out.print("👉 Scelta: ");
        return scanner.nextLine();
    }

    public void mostraAiuto() {
        System.out.println("""
            🆘 Guida Interfaccia:
            - Scheda Allenamento: Visualizza la scheda di allenamento assegnata.
            - Lista Attività: Visualizza la lista delle attività disponibili in modo da prenotarsi.
            - Stato Abbonamento: Visualizza lo stato di abbonamento ed eventualmente effettuare un rinnovo.
            - Lista Chat: Visualizza la lista delle chat con i trainer.
            - Codice D'Accesso: Visualizza il codice QR per accedere alla struttura.
        """);
    }

    public void pulisciSchermo() {
        for (int i = 0; i < 50; i++) System.out.println();
    }

    public void mostraMessaggio(String msg) {
        System.out.println(msg);
    }
}
