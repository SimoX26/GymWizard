package ispwproject.gymwizard.view;

import java.util.Scanner;

public class VisualizzaEserciziSchedaView {
    private final Scanner scanner = new Scanner(System.in);

    public String inserisciEsercizio() {
        System.out.println("=== AGGIUNGI ESERCIZIO ===");
        System.out.print("Nome esercizio: ");
        String nome = scanner.nextLine();

        System.out.print("Serie: ");
        String serie = scanner.nextLine();

        System.out.print("Ripetizioni: ");
        String ripetizioni = scanner.nextLine();

        return nome + " " + serie + "x" + ripetizioni;
    }
}
