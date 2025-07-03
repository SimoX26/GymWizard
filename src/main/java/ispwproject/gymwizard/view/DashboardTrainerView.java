package ispwproject.gymwizard.view;

import java.util.Scanner;

public class DashboardTrainerView {
    private final Scanner scanner = new Scanner(System.in);

    public void mostraBenvenuto(String username) {
        System.out.println("\n👋 Benvenuto " + (username != null ? username : "trainer") + "!");
    }

    public void mostraMenu() {
        System.out.println("""
            \n📋 DASHBOARD TRAINER:
            1. Lista Clienti
            2. Lista Chat
            3. Aiuto
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
            Puoi accedere alla lista dei clienti oppure alla chat con loro.
        """);
    }

    public void mostraMessaggio(String msg) {
        System.out.println(msg);
    }

    public void pulisciSchermo() {
        for (int i = 0; i < 50; i++) System.out.println();
    }
}
