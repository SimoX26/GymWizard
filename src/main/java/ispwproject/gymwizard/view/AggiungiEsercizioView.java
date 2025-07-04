package ispwproject.gymwizard.view;

import java.util.Scanner;

public class AggiungiEsercizioView {
    private final Scanner scanner = new Scanner(System.in);

    public String inserisciEsercizio(String scheda) {
        System.out.println("\n➕ AGGIUNGI ESERCIZIO ALLA SCHEDA: " + scheda);
        System.out.print("🏋️ Nome esercizio: ");
        return scanner.nextLine();
    }

    public int inserisciNumero(String prompt) {
        int numero;
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                numero = Integer.parseInt(input);
                if (numero <= 0) {
                    System.out.println("❌ Inserire un numero maggiore di zero.");
                } else {
                    return numero;
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Inserire un numero valido.");
            }
        }
    }

    public String inserisciNote() {
        System.out.print("📝 Note aggiuntive (facoltative): ");
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
