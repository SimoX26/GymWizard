package ispwproject.gymwizard.view;

import java.util.Scanner;

public class AggiungiEsercizioView {
    private final Scanner scanner = new Scanner(System.in);

    public String inserisciEsercizio(String scheda) {
        System.out.println("\n➕ AGGIUNGI ESERCIZIO A: " + scheda);
        System.out.print("Nome esercizio (es. Panca 4x10): ");
        return scanner.nextLine();
    }

    public void mostraMessaggio(String msg) {
        System.out.println(msg);
    }

    public void attesaInvio() {
        System.out.print("👉 Premi invio per continuare...");
        scanner.nextLine();
    }
}
