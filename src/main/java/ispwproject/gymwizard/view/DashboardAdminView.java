package ispwproject.gymwizard.view;

import java.util.Scanner;

public class DashboardAdminView {
    private final Scanner scanner = new Scanner(System.in);

    public void mostraBenvenuto(String username) {
        System.out.println("\n👋 Benvenuto " + (username != null ? username : "admin") + "!");
    }

    public void mostraMenu() {
        System.out.println("""
            \n🛠️ DASHBOARD AMMINISTRATORE:
            1. Gestione Listino Attività
            2. Visualizza Report e Statistiche
            3. Invia Comunicazioni
            4. Aiuto
            0. Logout
        """);
    }

    public String chiediScelta() {
        System.out.print("👉 Scelta: ");
        return scanner.nextLine();
    }

    public void mostraHelp() {
        System.out.println("""
            🆘 Guida Amministratore:
            - Gestione Listino: Aggiungi, modifica o rimuovi attività.
            - Report: Statistiche su utenti e performance.
            - Comunicazioni: Invia messaggi globali ai clienti o ai coach.
        """);
        System.out.print("👉 Premi invio per tornare al menu: ");
        scanner.nextLine();
    }

    public void mostraMessaggio(String msg) {
        System.out.println(msg);
    }

    public void attesaInvioPerContinuare() {
        System.out.print("👉 Premi invio per continuare: ");
        scanner.nextLine();
    }

    public void pulisciSchermo() {
        for (int i = 0; i < 50; i++) System.out.println();
    }
}
