package ispwproject.gymwizard.view;

import java.util.List;
import java.util.Scanner;

public class VisualizzaEserciziSchedaView {
    private final Scanner scanner = new Scanner(System.in);

    public int mostraScheda(String nome, List<String> esercizi, boolean trainerMode) {
        System.out.println("\n📄 SCHEDA: " + nome);
        for (int i = 0; i < esercizi.size(); i++) {
            System.out.println((i + 1) + ". " + esercizi.get(i));
        }

        System.out.println("\nOpzioni:");
        if (trainerMode) {
            System.out.println("1. ➕ Aggiungi esercizio");
            System.out.println("2. 🗑️ Svuota scheda");
        }
        System.out.println("0. 🔙 Torna indietro");

        System.out.print("👉 Scelta: ");
        String input = scanner.nextLine();

        return switch (input) {
            case "0" -> -1;
            case "1" -> trainerMode ? -2 : -1;
            case "2" -> trainerMode ? -3 : -1;
            default -> {
                System.out.println("❌ Scelta non valida.");
                yield mostraScheda(nome, esercizi, trainerMode);
            }
        };
    }

    public void mostraMessaggio(String msg) {
        System.out.println(msg);
    }

    public void attesaInvio() {
        System.out.print("👉 Premi invio per continuare...");
        scanner.nextLine();
    }
}
