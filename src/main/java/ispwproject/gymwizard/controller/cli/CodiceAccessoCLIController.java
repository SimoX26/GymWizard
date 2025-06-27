package ispwproject.gymwizard.controller.cli;

import java.util.Scanner;

public class CodiceAccessoCLIController {

    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("\n🔐 CODICE DI ACCESSO ALLA PALESTRA\n");

        /*
         Simulazione codice – normalmente sarebbe preso dal database o da un controller applicativo
         String username = SessionManager.getInstance().getSession().getUsername();
        String codiceAccesso = "GYM-" + (username != null ? username.hashCode() : "0000");
            System.out.println("🔑 Il tuo codice di accesso è: " + codiceAccesso);
        */

        System.out.println("⚠️ Mostralo all'ingresso per accedere alla struttura.");

        System.out.println("\n0. 🔙 Torna alla Dashboard");
        System.out.print("👉 Premi invio per tornare: ");
        scanner.nextLine();
    }
}
