package ispwproject.gymwizard.view;

import java.util.Scanner;

public class CreaAttivitaView {

    private final Scanner scanner = new Scanner(System.in);

    public void mostraTitolo() {
        System.out.println("\n➕ CREAZIONE NUOVA ATTIVITÀ");
    }

    public String chiediStringa(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public int chiediNumero(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int n = Integer.parseInt(scanner.nextLine().trim());
                if (n > 0) return n;
                else System.out.println("❌ Inserire un numero maggiore di 0.");
            } catch (NumberFormatException e) {
                System.out.println("❌ Inserire un numero valido.");
            }
        }
    }

    public void mostraMessaggio(String msg) {
        System.out.println(msg);
    }

    public void attesaInvio() {
        System.out.print("👉 Premi invio per continuare...");
        scanner.nextLine();
    }
}
