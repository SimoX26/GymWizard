package ispwproject.gymwizard.view;

import java.util.Scanner;

public class CreaSchedaClienteView {
    private final Scanner scanner = new Scanner(System.in);

    public String chiediNomeScheda(String cliente) {
        System.out.println("\n➕ CREA NUOVA SCHEDA PER: " + cliente);
        System.out.print("Inserisci nome scheda: ");
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
